package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class SamplePollutionRecord implements CommandLineRunner {

	private final PollutionDataRepository pollutionDataRepository;

	@Autowired
	public SamplePollutionRecord(PollutionDataRepository pollutionDataRepository) {
		this.pollutionDataRepository = pollutionDataRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Pollution pollutionData1 = new Pollution("Delhi",40);
		Pollution pollutionData2 = new Pollution("bombay",50);
		List<Pollution> pollutionDataList= new ArrayList<Pollution>();
		pollutionDataList.add(pollutionData1);
		pollutionDataList.add(pollutionData2);
		pollutionDataList.stream().forEach(x -> pollutionDataRepository.save(x));
		pollutionDataRepository.findAll().forEach(System.out::println);
	}
}