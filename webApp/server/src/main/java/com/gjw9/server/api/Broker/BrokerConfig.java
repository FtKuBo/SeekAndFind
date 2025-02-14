package com.gjw9.server.api.Broker;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class BrokerConfig {

    @Autowired 
    StreamBridge streamBridge;

	@Bean
	public Consumer<Message<String>> weAppConsumer() {
		return v -> {
			//match received
			System.out.println("Received myConsumer: " + v.getPayload());
			System.out.println("CorrelationID: " + v.getHeaders().get("solace_correlationId"));
		};
	}

}
