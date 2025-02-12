package com.gjw9.server.infra.Notification;

import com.gjw9.server.infra.UserProfile.UserProfile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //problem parceque c'est une entite
    // private UserProfile user;

    private String notification;

    public void setNotification(String notification){
        this.notification = notification;
    }

    public Long getId(){
        return this.id;
    }

    public String getNotification(){
        return this.notification;
    }

    // public String getName() {
    //     return user.getName();
    // }

    // public String getEmail() {
    //     return user.getEmail();
    // }

}
