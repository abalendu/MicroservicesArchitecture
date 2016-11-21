package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@MessageEndpoint
public class PollutionProcessor {

	private final PollutionDataRepository pollutionDataRepository;

	@Autowired
	public PollutionProcessor(PollutionDataRepository pollutionDataRepository) {
		this.pollutionDataRepository = pollutionDataRepository;
	}

	@ServiceActivator(inputChannel = "input")
	public void acceptNewPollution(Message<String> msg) {
		String rn = msg.getPayload();
		String[] payload = rn.split("/");
		this.pollutionDataRepository.save(new Pollution(payload[0],Long.parseLong(payload[1])));
		
		placeDataInHazelCast();
	}

	private void placeDataInHazelCast() {
		List<Pollution> pollution = pollutionDataRepository.findAll();
		HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance();
		List<Pollution> empMap = hazelcast.getList("employees");
		empMap.clear();
		empMap.addAll(pollution);
		return;
	}
}