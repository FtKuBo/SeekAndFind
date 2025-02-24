package com.gjw9.matchingServer.service.MatchingSys;

import java.util.Collection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.FoundObject.FoundObject;
import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObject;
import com.gjw9.matchingServer.service.Email.EmailService;
import com.gjw9.matchingServer.service.FoundObj.FoundObjService;
import com.gjw9.matchingServer.service.LostObj.LostObjService;

@Service
public class MatchingSysService {

    @Autowired
    FoundObjService foundObjService;

    @Autowired
    LostObjService lostObjService;

    @Autowired
    EmailService emailService;

    public void newLostObj(LostObject obj){
        Collection<FoundObject> matches = seekMatchForLostObj(obj);
        if (matches !=  null){
            for (FoundObject match : matches){
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("foundUserEmail", match.getUserEmail());
                jsonObj.put("lostUserEmail", obj.getUserEmail());
                jsonObj.put("foundDate", match.getObjectDate());
                jsonObj.put("lostDate", obj.getObjectDate());
                jsonObj.put("foundObjectDescription", match.getObjectDescription());
                jsonObj.put("lostObjectDescription", obj.getObjectDescription());
                jsonObj.put("objectLocation", match.getObjectLocation());
                jsonObj.put("objectType", match.getObjectType());
                jsonObj.put("objectBrand", match.getObjectBrand());
                sendMatch(jsonObj);
            }
        }
        else{
            lostObjService.saveLostObject(obj);
        }
    }

    public void newFoundObj(FoundObject obj){
        Collection<LostObject> matches = seekMatchForFoundObj(obj);
        if (matches !=  null){
            for (LostObject match : matches){
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("lostUserEmail", match.getUserEmail());
                jsonObj.put("foundUserEmail", obj.getUserEmail());
                jsonObj.put("foundDate", obj.getObjectDate());
                jsonObj.put("lostDate", match.getObjectDate());
                jsonObj.put("foundObjectDescription", obj.getObjectDescription());
                jsonObj.put("lostObjectDescription", match.getObjectDescription());
                jsonObj.put("objectLocation", match.getObjectLocation());
                jsonObj.put("objectType", match.getObjectType());
                jsonObj.put("objectBrand", match.getObjectBrand());
                sendMatch(jsonObj);
            }
        }
        else{
            foundObjService.saveFoundObject(obj);
        }
    }

    private Collection<FoundObject> seekMatchForLostObj(LostObject obj){
        Collection<FoundObject> match = foundObjService.getFoundObjects(obj.getUserEmail(), obj.getObjectType(), obj.getObjectBrand(), obj.getObjectLocation(), obj.getObjectDate());

        if (match.size() > 0)
            return match;

        return null;
    }
    
    private Collection<LostObject> seekMatchForFoundObj(FoundObject obj){
        Collection<LostObject> match = lostObjService.getFoundObjects(obj.getUserEmail(), obj.getObjectType(), obj.getObjectBrand(), obj.getObjectLocation(), obj.getObjectDate());
        
        if (match.size() > 0)
            return match;

        return null;
    }

    private JSONObject sendMatch(JSONObject match){
        String FoundEmailBody = generateEmail(match.getString("lostUserEmail"), "lost", match.getString("lostObjectDescription"), match.get("lostDate").toString(), match.getString("objectType"), match.getString("objectBrand"), match.getString("objectLocation"));
        emailService.sendEmail(match.getString("foundUserEmail"), "Subject: SeekAndFind Notification – " + match.getString("objectBrand") + " " +  match.getString("objectType") + "matched", FoundEmailBody);

        String LostEmailBody = generateEmail(match.getString("foundUserEmail"), "found", match.getString("foundObjectDescription"), match.get("foundDate").toString(), match.getString("objectType"), match.getString("objectBrand"), match.getString("objectLocation"));
        emailService.sendEmail(match.getString("lostUserEmail"), "Subject: SeekAndFind Notification – " + match.getString("objectBrand") + " " +  match.getString("objectType") + "matched", LostEmailBody);

        return match;
    }


    private String generateEmail(String userEmail, String objStatus, String objDescription, String date, String objType, String objBrand, String location) {
        return String.format(
            "Hello,\n\n" +
            "We are reaching out to inform you that an item matching your description has been found.\n\n" +
            "**Item Description:** %s\n" +
            "**Date %s:** %s\n" +
            "**Item Type:** %s\n" +
            "**Item Brand:** %s\n" +
            "**Location %s:** %s\n\n" +
            "Please contact this email at your earliest convenience to arrange for possible retrieval: %s\n\n" +
            "Best regards,\n" +
            "SeekAndFind Team",
            objDescription, objStatus, date, objType, objBrand, objStatus, location, userEmail
        );
    }

}
