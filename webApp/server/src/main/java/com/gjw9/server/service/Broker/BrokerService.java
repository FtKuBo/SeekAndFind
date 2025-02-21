package com.gjw9.server.service.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import org.json.JSONObject;

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

    static String EMAILSBJ = "";

    public void sendMessage(String topic, Request message){
        streamBridge.send(topic, message);
    }

    public void handleMessage(String message){
        if (message != null){
            JSONObject matchObject = new JSONObject(message);
            String foundUserEmail = matchObject.getString("foundUserEmail");
            String lostUserEmail = matchObject.getString("lostUserEmail");

            String emailBodyForFoundUser = generateEmail(lostUserEmail, "lost", matchObject.getString("lostObjectDescription"), matchObject.getString("objectDate"), matchObject.getString("objectType"), matchObject.getString("objectLocation"));
            String emailBodyForLostUser = generateEmail(foundUserEmail, "found", matchObject.getString("foundObjectDescription"), matchObject.getString("objectDate"), matchObject.getString("objectType"), matchObject.getString("objectLocation"));

            //sending email to the user that lost the obj
            emailService.sendEmail(lostUserEmail,EMAILSBJ,emailBodyForLostUser);

            //sending email to the user that found the obj
            emailService.sendEmail(foundUserEmail, EMAILSBJ, emailBodyForFoundUser);
        }
    }


    private String generateEmail(String userEmail, String objStatus, String objDescription, String dateFound, String objType, String location) {
        return String.format(
            "Subject: SeekAndFind Notification – %s matched\n\n" +
            "Hello,\n\n" +
            "We are reaching out to inform you that an item matching your description has been found.\n\n" +
            "**Item Description:** %s\n" +
            "**Date %s:** %s\n" +
            "**Item Type:** %s\n" +
            "**Location %s:** %s\n\n" +
            "Please contact this email at your earliest convenience to arrange for possible retrieval: %s\n\n" +
            "Best regards,\n" +
            "SeekAndFind Team",
            objType, objDescription, objStatus, dateFound, objType, objStatus, location, userEmail
        );
    }

}
