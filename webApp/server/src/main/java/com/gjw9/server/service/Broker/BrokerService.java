package com.gjw9.server.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;

@Service
public class BrokerService {

    @Autowired
    StreamBridge streamBridge;

    public Request sendMessage(String topic, Request message){
        // format Request to proper message
        streamBridge.send(topic, "request received");
        return message;
    }

    public void handleMessage(String message){
        // do something with message
    }
}
