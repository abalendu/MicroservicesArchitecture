package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;


@RestController
@RefreshScope
class MessageRestController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/message")
	public List<Pollution> read() {

		List<Pollution> listobj = HazelcastClient.newHazelcastClient(new ClientConfig()).getList("employees");
		List<Pollution> newListObj = new ArrayList<Pollution>();
		listobj.forEach((x) -> newListObj.add(x));
		newListObj.sort((Pollution o1, Pollution o2) -> Long.compare(o1.getPollutionLevel() , o2.getPollutionLevel()));
		newListObj.forEach((x) -> System.out.println(x.getPollutedCities()));
		return newListObj;
	}

	
	private final String value;

	@Autowired
	public MessageRestController(
			@Value("${message}") String value) {
		this.value = value;
	}

}