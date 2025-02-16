package com.gjw9.matchingServer.service.MatchingSys;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.gjw9.matchingServer.infra.MatchingSys.MatchObject;
import com.gjw9.matchingServer.infra.MatchingSys.FoundObject.FoundObject;
import com.gjw9.matchingServer.infra.MatchingSys.LostObject.LostObject;
import com.gjw9.matchingServer.service.FoundObj.FoundObjService;
import com.gjw9.matchingServer.service.LostObj.LostObjService;

@Service
public class MatchingSysService {

    @Autowired
    FoundObjService foundObjService;

    @Autowired
    LostObjService lostObjService;

    @Autowired
    StreamBridge streamBridge;

    public void newLostObj(LostObject obj){
        Collection<FoundObject> matches = seekMatchForLostObj(obj);

        if (matches !=  null){
            for (FoundObject match : matches)
                sendMatch(match);
        }
        else{
            lostObjService.saveLostObject(obj);
        }
    }

    public void newFoundObj(FoundObject obj){
        Collection<LostObject> matches = seekMatchForFoundObj(obj);

        if (matches !=  null){
            for (LostObject match : matches)
                sendMatch(match);
        }
        else{
            foundObjService.saveFoundObject(obj);
        }
    }

    private Collection<FoundObject> seekMatchForLostObj(LostObject obj){
        Collection<FoundObject> match = foundObjService.getFoundObjects(obj.getUserEmail(), obj.getObjectType(), obj.getObjectLocation(), obj.getObjectDate());

        if (match.size() > 0)
            return match;

        return null;
    }
    
    private Collection<LostObject> seekMatchForFoundObj(FoundObject obj){
        Collection<LostObject> match = lostObjService.getFoundObjects(obj.getUserEmail(), obj.getObjectType(), obj.getObjectLocation(), obj.getObjectDate());
        
        if (match.size() > 0)
            return match;

        return null;
    }

    private MatchObject sendMatch(MatchObject match){
        streamBridge.send("object/match", match);
        return match;
    }

}
