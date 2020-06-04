package com.cecile.test.domaine;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfile;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String email;
    private String telephone;
    private String companyName;

    @NotNull
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "profile",cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    private List<BusinessCard> businessCards = new ArrayList<>();


    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
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

    public List<BusinessCard> getBusinessCards() {
        return businessCards;
    }

    public void setBusinessCards(List<BusinessCard> businessCards) {
        this.businessCards = businessCards;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
