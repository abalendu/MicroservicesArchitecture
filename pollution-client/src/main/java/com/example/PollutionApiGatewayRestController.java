package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/pollution")
public class PollutionApiGatewayRestController {

	private final RestTemplate restTemplate;

	private final MessageChannel pollutionService;

	@Autowired
	public PollutionApiGatewayRestController(PollutionClientChannels channels,
			@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.pollutionService = channels.output();
	}

	public Collection<String> fallback() {
		return new ArrayList<>();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/rank")
	public List names() {
		return restTemplate.getForObject("http://pollution-ranking-service" + "//message", List.class);
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names1")
	public Collection<String> names1() {

		ParameterizedTypeReference<Resources<Pollution>> ptr = new ParameterizedTypeReference<Resources<Pollution>>() {
		};

		ResponseEntity<Resources<Pollution>> responseEntity = this.restTemplate
				.exchange("http://pollution-service/pollutions", HttpMethod.GET, null, ptr);

		List<String> pollutionMap = responseEntity.getBody().getContent().stream().map(Pollution::getPollutedCities)
				.collect(Collectors.toList());

		return pollutionMap;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void writePollutionData(@RequestBody Pollution pollution) {
		/**/

		String city = pollution.getCity();
		long pollutionLevel = pollution.getPollutionLevel();

		Message<String> msg = MessageBuilder.withPayload(city + "/" + pollutionLevel).build();

		this.pollutionService.send(msg);
	}
}
