package com.gjw9.server;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}


	// @Bean
	// public Consumer<Message<String>> myConsumer(StreamBridge sb) {
	// 	return v -> {
	// 		//match received
	// 		System.out.println("Received myConsumer: " + v.getPayload());
	// 		System.out.println("CorrelationID: " + v.getHeaders().get("solace_correlationId"));

	// 		// object found
	// 		String myTopic = "object/found";
	// 		System.out.println("Publishing to: " + myTopic);
	// 		sb.send(myTopic, "object found");

	// 		// object lost
	// 		myTopic = "object/lost";
	// 		System.out.println("Publishing to: " + myTopic);
	// 		sb.send(myTopic, "object lost");
	// 	};
	// }

}
