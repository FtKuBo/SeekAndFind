package com.gjw9.server.service.Email;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String request){
        System.out.println("sending email " + request);
    }
}
