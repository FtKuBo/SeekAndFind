package com.gjw9.server.service.UserProfile;
 
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gjw9.server.infra.UserProfile.UserProfile;
import com.gjw9.server.infra.UserProfile.UserProfileRepository;

@Service
public class UserProfileService {
 
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder encoder;
     
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

    public UserProfile saveNewUserProfile(UserProfile newUser) 
            throws IllegalStateException {
        try{
            getUser(newUser.getEmail());
            throw new IllegalStateException();
        }
        catch(NoSuchElementException e){
            newUser.setPassword( encoder.encode(newUser.getPassword()) );

            userProfileRepository.saveAndFlush(newUser);
    
            return newUser;
        }
    }

    public UserProfile saveUserProfile(UserProfile updatedUser) {
            updatedUser.setPassword( encoder.encode(updatedUser.getPassword()) );

            userProfileRepository.saveAndFlush(updatedUser);

            return updatedUser;
        }

    public boolean authenticateUser(String password, UserProfile user) {
        return encoder.matches(password, user.getPassword());
    }
}
 
