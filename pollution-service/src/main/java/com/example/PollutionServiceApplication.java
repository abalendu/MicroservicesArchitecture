package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(PollutionServiceChannels.class)
@EnableDiscoveryClient
@SpringBootApplication
public class PollutionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollutionServiceApplication.class, args);
	}
}
