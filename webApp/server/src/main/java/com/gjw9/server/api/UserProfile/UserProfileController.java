package com.gjw9.server.api.UserProfile;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.service.UserProfile.UserProfileService;

@RestController
@RequestMapping(path = "/userProfiles")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping(path = "/auth")
    public @ResponseBody Boolean getUserProfile(@RequestParam String email, @RequestParam String password) {

        try{
            UserProfile userProfile =  userProfileService.getUser(email);
        
            return userProfileService.authenticateUser(password, userProfile);
        }
        catch(NoSuchElementException e){
            System.out.println("User doesn't exist");
            
            return false;
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody UserProfile addNewUser(@RequestBody UserProfile newUser ) {
        try{
            UserProfile user = userProfileService.saveNewUserProfile(newUser);

            return user;
        }
        catch(IllegalStateException e){
            System.out.println("User already exists");

            return null;
        }
    }

    @PutMapping(path = "/put")
    public @ResponseBody UserProfile updateUser(@RequestBody UserProfile updatedUser ) {
        UserProfile user = userProfileService.saveUserProfile(updatedUser);

        return user;
    }

    @DeleteMapping(path = "/del")
    public @ResponseBody void deleteUserByEmail(@RequestParam String email) {
        userProfileService.deleteUserByEmail(email);
        
    }

}
