package com.uga.kafka_consumer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uga.kafka_consumer.model.User;
import com.uga.kafka_consumer.model.UserResponse;

@RestController
public class KafkaRestController {

	@PostMapping("/receiveMsg")
	public ResponseEntity<UserResponse> sendMessage(@RequestBody User user) {
		System.out.println("The User Data Received - "+user);
		UserResponse response = new UserResponse("Success", null);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
}
