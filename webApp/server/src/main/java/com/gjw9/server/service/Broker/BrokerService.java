package com.gjw9.server.service.Broker;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.service.Email.EmailService;

//TODO FIND A WAY TO DELETE REQUESTS AND SHOW IN FRONT THAT THEY WERE DELETED ONLY IF IT IS THE CASE
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
            System.out.println(message);
            // emailService.sendEmail(message);
            //             JSONObject jsonObj = new JSONObject(obj);

            // FoundObject foundObject = new FoundObject();

            // foundObject.setUserEmail(jsonObj.getString("userEmail"));
            // foundObject.setObjectDescription(jsonObj.getString("objectDescription"));
            // foundObject.setObjectType(jsonObj.getString("objectType"));
            // foundObject.setObjectLocation(jsonObj.getString("objectLocation"));
            // foundObject.setObjectDate(LocalDate.parse(jsonObj.getString("objectDate")));

            // return foundObject;
        }
    }
}
