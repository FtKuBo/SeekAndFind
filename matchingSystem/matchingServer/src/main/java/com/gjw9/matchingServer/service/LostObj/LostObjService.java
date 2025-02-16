package com.gjw9.matchingServer.service.LostObj;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObjRepository;
import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObject;

@Service
public class LostObjService {
    @Autowired
    LostObjRepository lostObjRepository;

    public Collection<LostObject> getFoundObjects(String email, String objType, String objLocation, LocalDate objDate) {
        Collection<LostObject> lostObjects = lostObjRepository.getAllMatchingObjects(email, objType, objLocation, objDate);

        return lostObjects;
    }

    public void deleteFoundObject(LostObject lostObject) {
        lostObjRepository.deleteById(lostObject.getId());
    }

    public LostObject saveLostObject(LostObject lostObject) {
        lostObjRepository.saveAndFlush(lostObject);
    
        return lostObject;
    }
}
