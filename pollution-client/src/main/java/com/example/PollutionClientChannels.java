package com.example;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PollutionClientChannels {

	@Output
	MessageChannel output();
}