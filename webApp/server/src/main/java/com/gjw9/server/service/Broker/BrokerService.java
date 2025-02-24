package com.gjw9.server.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;

@Service
public class BrokerService {

    @Autowired
    StreamBridge streamBridge;

    public void sendMessage(String topic, Request message){
        streamBridge.send(topic, message);
    }

}
