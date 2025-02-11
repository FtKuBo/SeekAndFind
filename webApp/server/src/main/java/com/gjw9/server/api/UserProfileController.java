package com.gjw9.server.api;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.service.UserProfileService;

@RestController
@RequestMapping(path = "/userProfiles")
public class UserProfileController {

    UserProfileService userProfileService = new UserProfileService();

// // To change error handling
//     @GetMapping(path = "/auth")
//     public @ResponseBody Boolean getUserProfile(@RequestParam String email, String password) {

//         try{
//             UserProfile userProfile =  userProfileService.getUser(email);
        
//             return userProfileService.authenticateUser(password, userProfile);
//         }
//         catch(DataIntegrityViolationException e){
//             System.out.println("User doesn't exist or password not valid");
//             return null;
//         }
//     }

// To change error handling
    @PostMapping(path = "/add")
    public @ResponseBody UserProfile addNewUser(@RequestBody String userInfo ) {
        try{
            String name = extractValue(userInfo, "name");
            String email = extractValue(userInfo, "email");
            String password = extractValue(userInfo, "password");
            UserProfile user = userProfileService.saveUserProfile(name, email, password);

            return user;
        }
        catch(DataIntegrityViolationException e){
            System.out.println("user email already exists");

            return null;
        }
    }
// temporary just for testing purposes
    private static String extractValue(String jsonString, String key) {
        // Find the start of the value (after the colon and space)
        int startIndex = jsonString.indexOf("\"" + key + "\"") + key.length() + 3;  // +3 for the ": " and opening quote
        
        // Find the end of the value (the closing quote)
        int endIndex = jsonString.indexOf("\"", startIndex);
        
        // Return the value between the quotes
        return jsonString.substring(startIndex, endIndex);
    }

    // @PutMapping(path = "/put")
    // public @ResponseBody Iterable<UserProfile> getAllUsers() {
    //     // This returns a JSON or XML with the users
    //     return userRepository.findAll();
    // }

    @DeleteMapping(path = "/del")
    public void getAllUsers(@RequestParam String email) {
        userProfileService.deleteUserByEmail(email);
        
    }

}
