package com.gjw9.server.infra;

import com.gjw9.server.infra.UserProfile.UserProfile;

// to implement
public class Notification {

    private UserProfile user;
    private String notification;

    public Notification(UserProfile user){
        this.user = user;
    }

    public void setNotification(String notification){
        this.notification = notification;
    }

    public String getNotification(){
        return this.notification;
    }

    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

}
