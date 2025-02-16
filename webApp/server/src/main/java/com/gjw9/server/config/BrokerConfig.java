package com.gjw9.server.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.gjw9.server.service.Broker.BrokerService;

@Configuration
public class BrokerConfig {

    @Autowired 
    StreamBridge streamBridge;

    @Autowired
    BrokerService brokerService;

	@Bean
	public Consumer<Message<String>> weAppConsumer() {
		return v -> {
            System.out.println("message received " + v.getPayload());
            brokerService.handleMessage(v.getPayload());
		};
	}

}
