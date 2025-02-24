package com.gjw9.matchingServer.infra.MatchingSys.LostObject;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LostObjects")
public class LostObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userEmail;

    private String objectDescription;

    private String objectType;

    private String objectBrand;

    private String objectLocation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate objectDate;

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectBrand() {
        return objectBrand;
    }

    public void setObjectBrand(String objectBrand) {
        this.objectBrand = objectBrand;
    }

    public String getObjectLocation() {
        return objectLocation;
    }

    public void setObjectLocation(String objectLocation) {
        this.objectLocation = objectLocation;
    }

    public LocalDate getObjectDate() {
        return objectDate;
    }

    public void setObjectDate(LocalDate objectDate) {
        this.objectDate = objectDate;
    }
}
