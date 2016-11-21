package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
class MessageRestController {

	@RequestMapping(method = RequestMethod.GET, value = "/message")
	public String read() {
		return this.value;
	}

	
	private final String value;

	@Autowired
	public MessageRestController(
			@Value("${message}") String value) {
		this.value = value;
	}

}