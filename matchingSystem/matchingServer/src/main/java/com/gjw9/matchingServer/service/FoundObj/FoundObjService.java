package com.gjw9.matchingServer.service.FoundObj;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.FoundObject.FoundObjRepository;
import com.gjw9.matchingServer.infra.MatchingSys.FoundObject.FoundObject;

import jakarta.transaction.Transactional;

@Service
public class FoundObjService {


    @Autowired
    FoundObjRepository foundObjRepository;

    public Collection<FoundObject> getFoundObjects(String email, String objType, String objBrand, String objLocation, LocalDate objDate) {
        Collection<FoundObject> foundObjects = foundObjRepository.getAllMatchingObjects(email, objType, objBrand, objLocation, objDate);
        System.out.println(foundObjects);
        return foundObjects;
    }

    @Transactional
    public void deleteFoundObject(FoundObject foundObject) {
        foundObjRepository.deleteClones(foundObject.getUserEmail(), foundObject.getObjectType(), foundObject.getObjectBrand(), foundObject.getObjectDescription(), foundObject.getObjectLocation(), foundObject.getObjectDate());
    }

    @Transactional
    public FoundObject saveFoundObject(FoundObject foundObject) {
        foundObjRepository.saveAndFlush(foundObject);
    
        return foundObject;
    }
}
