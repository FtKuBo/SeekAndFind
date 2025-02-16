package com.gjw9.matchingServer.config.Broker;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.gjw9.matchingServer.service.Broker.BrokerService;



@Configuration
public class BrokerConfig {

    @Autowired 
    StreamBridge streamBridge;

    @Autowired
    BrokerService brokerService;

	@Bean
	public Consumer<Message<String>> lostObject() {
		return v -> {
            brokerService.handleLostObject(v.getPayload());
		};
	}

    @Bean
	public Consumer<Message<String>> foundObject() {
		return v -> {
            brokerService.handleFoundObject(v.getPayload());
		};
	}

}
