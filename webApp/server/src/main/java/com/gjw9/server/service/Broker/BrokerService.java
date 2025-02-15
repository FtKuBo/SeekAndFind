package com.gjw9.server.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.service.Email.EmailService;


@Service
public class BrokerService {

    @Autowired
    StreamBridge streamBridge;

    @Autowired 
    EmailService emailService;

    public Request sendMessage(String topic, Request message){
        streamBridge.send(topic, message);
        return message;
    }
// TODO : extract the user emails and content
    public void handleMessage(String message){
        if (message != null){
            emailService.sendEmail(message);
        }
    }
}
