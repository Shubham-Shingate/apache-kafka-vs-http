package com.uga.kafka_consumer.listener;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.uga.kafka_consumer.model.User;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "java-topic", group = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }


	@KafkaListener(topics = "javainuse-topic", group = "group_json", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(User user) {
		System.out.println("Consumed JSON Message: " + user);
	}
 
}
