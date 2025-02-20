package com.gjw9.matchingServer.service.Broker;

import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.FoundObject.FoundObject;
import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObject;
import com.gjw9.matchingServer.service.FoundObj.FoundObjService;
import com.gjw9.matchingServer.service.LostObj.LostObjService;
import com.gjw9.matchingServer.service.MatchingSys.MatchingSysService;


@Service
public class BrokerService {

    @Autowired
    StreamBridge streamBridge;

    @Autowired
    MatchingSysService matchingSysService;

    @Autowired 
    FoundObjService foundObjService;

    @Autowired
    LostObjService lostObjService;

    public void handleDelete(String message){
        JSONObject jsonObj = new JSONObject(message);
        String objStatus = jsonObj.getString("objectStatus");

        if (objStatus == "found"){
            FoundObject newObj = JsonToFoundObj(message);
            foundObjService.deleteFoundObject(newObj);

        }
        if (objStatus == "lost"){
            LostObject newObj = JsonToLostObj(message);
            System.out.println("deleting obj" + message);
            lostObjService.deleteLostObject(newObj);
        }
    }

    public void handleFoundObject(String message){
        FoundObject newObj = JsonToFoundObj(message);

        if (newObj != null){
            matchingSysService.newFoundObj(newObj);
        }
    }

    private FoundObject JsonToFoundObj(String obj){
        try {
            JSONObject jsonObj = new JSONObject(obj);

            FoundObject foundObject = new FoundObject();

            foundObject.setUserEmail(jsonObj.getString("userEmail"));
            foundObject.setObjectDescription(jsonObj.getString("objectDescription"));
            foundObject.setObjectType(jsonObj.getString("objectType"));
            foundObject.setObjectLocation(jsonObj.getString("objectLocation"));
            foundObject.setObjectDate(LocalDate.parse(jsonObj.getString("objectDate")));

            return foundObject;
        }
        catch(Exception e){
            return null;
        }
    }

    public void handleLostObject(String message){
        LostObject newObj = JsonToLostObj(message);

        if (newObj != null){
            matchingSysService.newLostObj(newObj);
        }
    }

    private LostObject JsonToLostObj(String obj){
        try {
            JSONObject jsonObj = new JSONObject(obj);

            LostObject lostObject = new LostObject();

            lostObject.setUserEmail(jsonObj.getString("userEmail"));
            lostObject.setObjectDescription(jsonObj.getString("objectDescription"));
            lostObject.setObjectType(jsonObj.getString("objectType"));
            lostObject.setObjectLocation(jsonObj.getString("objectLocation"));
            lostObject.setObjectDate(LocalDate.parse(jsonObj.getString("objectDate")));

            return lostObject;
        }
        catch(Exception e){
            return null;
        }
    }

}
