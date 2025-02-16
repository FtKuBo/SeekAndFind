package com.gjw9.matchingServer.service.Broker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;


@Service
public class BrokerService {

    @Autowired
    StreamBridge streamBridge;

// TODO : extract the user emails and content and choose in which db store the data
    public void handleMessage(String message){
        if (message != null){
            System.out.println("storing data");
        }
    }
}
