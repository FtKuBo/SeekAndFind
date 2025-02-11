package com.gjw9.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.service.UserProfileService;

@RestController
@RequestMapping(path = "/userProfiles")
public class UserProfileController {
    @Autowired
    UserProfileService userProfileService;

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

    @PostMapping(path = "/add")
    public @ResponseBody UserProfile addNewUser(@RequestBody UserProfile newUser ) {
        try{
            UserProfile user = userProfileService.saveNewUserProfile(newUser);

            return user;
        }
        catch(IllegalStateException e){
            System.out.println("user email already exists");

            return null;
        }
    }

    // @PutMapping(path = "/put")
    // public @ResponseBody Iterable<UserProfile> getAllUsers() {
    //     // This returns a JSON or XML with the users
    //     return userRepository.findAll();
    // }

    @DeleteMapping(path = "/del/{email}")
    public @ResponseBody void getAllUsers(@PathVariable String email) {
        userProfileService.deleteUserByEmail(email);
        
    }

}
