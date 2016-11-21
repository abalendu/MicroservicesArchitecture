package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

interface PollutionServiceChannels {
	@Input
	SubscribableChannel input();
}