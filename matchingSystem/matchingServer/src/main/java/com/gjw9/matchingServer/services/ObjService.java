package com.gjw9.matchingServer.services;

import com.gjw9.matchingServer.database.entity.ObjPerdus;
import com.gjw9.matchingServer.database.entity.ObjTrouve;
import com.gjw9.matchingServer.database.repository.foundObjRepository;
import com.gjw9.matchingServer.database.repository.lostObjRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjService {

    @Autowired
    private foundObjRepository foundObjRepository;

    @Autowired
    private lostObjRepository lostObjRepository;

    public void saveObjLost(String marque, String typeObjet, String lieu, String description, String email, boolean estTrouve){
        System.out.println("called");
        ObjPerdus object = new ObjPerdus();
        object.setMarque(marque);
        object.setTypeObjet(typeObjet);
        object.setLieu(lieu);
        object.setDescription(description);
        object.setUtilisateurEmail(email);
        lostObjRepository.save(object);
    }

    public void saveObjFound(String marque, String typeObjet, String lieu, String description, String email, boolean estTrouve){
        ObjTrouve object = new ObjTrouve();
        object.setMarque(marque);
        object.setTypeObjet(typeObjet);
        object.setLieu(lieu);
        object.setDescription(description);
        object.setUtilisateurEmail(email);
        foundObjRepository.save(object);
    }

    public Iterable<ObjPerdus> matchesLost() {
        System.out.println("called");
        return lostObjRepository.findAll();
    }

    // public Iterable<ObjTrouve> matchesFound() {
    //     return foundObjRepository.findAll();
    // }

    // public Iterable<ObjPerdus> deleteLost() {
    //     return foundObjRepository.findAll();
    // }

    // public Iterable<ObjTrouve> deleteFound() {
    //     return foundObjRepository.findAll();
    // }

}