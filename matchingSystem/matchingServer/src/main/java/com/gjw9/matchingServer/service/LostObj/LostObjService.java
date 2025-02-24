package com.gjw9.matchingServer.service.LostObj;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObjRepository;
import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObject;

import jakarta.transaction.Transactional;

@Service
public class LostObjService {
    @Autowired
    LostObjRepository lostObjRepository;

    public Collection<LostObject> getFoundObjects(String email, String objType, String objBrand, String objLocation, LocalDate objDate) {
        Collection<LostObject> lostObjects = lostObjRepository.getAllMatchingObjects(email, objType, objBrand, objLocation, objDate);

        return lostObjects;
    }

    @Transactional
    public void deleteLostObject(LostObject lostObject) {
        lostObjRepository.deleteClones(lostObject.getUserEmail(), lostObject.getObjectType(), lostObject.getObjectBrand(), lostObject.getObjectDescription(), lostObject.getObjectLocation(), lostObject.getObjectDate());
    }

    @Transactional
    public LostObject saveLostObject(LostObject lostObject) {
        lostObjRepository.saveAndFlush(lostObject);
    
        return lostObject;
    }
}
