package com.cecile.test.domaine;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class BusinessCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBusinessCard;
    private String name;

    @NotNull
    @Column(nullable = false)
    private String email;


    private String telephone;
    private String companyName;

    @ManyToOne
    private Profile profile;

    public int getIdBusinessCard() {
        return idBusinessCard;
    }

    public void setIdBusinessCard(int idBusinessCard) {
        this.idBusinessCard = idBusinessCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
