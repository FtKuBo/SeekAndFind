package com.gjw9.server.service;
 
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.infra.UserProfile.UserProfileRepository;

 
public class UserProfileService {
 
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
     
    public UserProfile getUser(String email)
            throws NoSuchElementException{
        Optional<UserProfile> user = userProfileRepository.findById(email);
        
        if (!user.isPresent()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }

    public void deleteUserByEmail(String email) {
        userProfileRepository.deleteById(email);
    }

    public UserProfile saveUserProfile(String name, String email, String password) 
            throws DataIntegrityViolationException{
        UserProfile newUser = new UserProfile();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        
        userProfileRepository.save(newUser);

        return newUser;
    }

    public boolean authenticateUser(String password, UserProfile user) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
 
