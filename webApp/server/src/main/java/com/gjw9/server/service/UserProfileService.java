package com.gjw9.server.service;
 
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.infra.UserProfile.UserProfileRepository;
 
public class UserProfileService {
 
    @Autowired
    private UserProfileRepository userProfileRepository;
     
    public UserProfile getUserByEmail(String email)
            throws NoSuchElementException{
        UserProfile user = userProfileRepository.findOne(email);
         
        if (user == null) {
            throw new NoSuchElementException("Could not find user");
        }
         
        return user;
    }

    public void deleteUserByEmail(UserProfile user) {
        userProfileRepository.delete(user);
    }
}
 
