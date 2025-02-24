package com.gjw9.server.service.Request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.Request.Request;
import com.gjw9.server.infra.Request.RequestRepository;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Collection<Request> getRequests(String email) {
        Collection<Request> requests = requestRepository.getAllRequestByUserEmail(email);

        return requests;
    }

    public void deleteRequest(long id) {
        requestRepository.deleteById(id);
    }

    public Request saveRequest(Request request) {
        requestRepository.saveAndFlush(request);
    
        return request;
    }

}
