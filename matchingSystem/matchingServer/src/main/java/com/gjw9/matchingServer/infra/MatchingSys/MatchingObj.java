package com.gjw9.matchingServer.infra.MatchingSys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MatchingObjects")
public class MatchingObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String requestForm;

    public Long getId(){
        return this.id;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    public String getRequestForm(){
        return this.requestForm;
    }

    public void setUserEmail(String email){
        this.userEmail = email;
    }

    public void setRequestForm(String form){
        this.requestForm = form;
    }
}
