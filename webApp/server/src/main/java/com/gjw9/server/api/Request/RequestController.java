package com.gjw9.server.api.Request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.service.Request.RequestService;

@RestController
@RequestMapping(path="/requests")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping(path = "/get")
    public @ResponseBody List<Request> getRequestsByEmail(@RequestParam String email) {
        List<Request> requests = new ArrayList<>(requestService.getRequests(email));
       
        return requests;
    }

    @PostMapping(path = "/add")
    public @ResponseBody Request addNewRequest(@RequestBody Request newRequest ) {
        Request request = requestService.saveRequest(newRequest);
        
        return request;
    }

    @DeleteMapping(path = "/del")
    public @ResponseBody void deleteRequest(@RequestParam long id) {
        requestService.deleteRequest(id);
    }

}
