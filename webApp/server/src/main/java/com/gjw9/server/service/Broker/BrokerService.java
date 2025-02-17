package com.gjw9.server.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.service.Email.EmailService;

//TODO FIND A WAY TO DELETE REQUESTS AND SHOW IN FRONT THAT THEIR WERE DELETED ONLY IF IT IS THE CASE
//Change email auth
//finish implementing email system
//work on frontend

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

    public void handleMessage(String message){
        if (message != null){
            System.out.println("message received" + message);
            // emailService.sendEmail(message);
        }
    }
}
