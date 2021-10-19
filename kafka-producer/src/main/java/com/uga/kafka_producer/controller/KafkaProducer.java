package com.uga.kafka_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.uga.kafka_producer.model.User;
import com.uga.kafka_producer.model.UserResponse;

@RestController
public class KafkaProducer {

	@Value("${topic.name}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/publishMessage")
	public String publishMessage (@RequestParam String name, @RequestParam String dept) {
		long t1 = System.currentTimeMillis();
		kafkaTemplate.send(topic, new User(name, dept));
		long t2 = System.currentTimeMillis();
		return "Message Sent to Kafka cluster sucessfully in: "+(t2-t1)+" ms";
	}
	
	@GetMapping("/sendMessage")
	public String sendMessage (@RequestParam String name, @RequestParam String dept) {
		
		//Make an API call to email_service to send email
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		User user = new User(name, dept);
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		long t1 = System.currentTimeMillis();
		ResponseEntity<UserResponse> userService =  restTemplate.postForEntity("http://localhost:8082/receiveMsg", entity, UserResponse.class);
		long t2 = System.currentTimeMillis();
		return "Message Sent via HTTP in: "+(t2-t1)+" ms";
	}

	
}
