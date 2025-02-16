package com.gjw9.server.api.Broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.service.Broker.BrokerService;

@RestController
@RequestMapping(path = "/broker")
public class BrokerController {

    @Autowired
    BrokerService brokerService;

    @PostMapping(path = "/sendMessage")
    public @ResponseBody void sendMessage(@RequestParam String topic, @RequestBody Request message) {
        brokerService.sendMessage(topic, message);
    }

}
