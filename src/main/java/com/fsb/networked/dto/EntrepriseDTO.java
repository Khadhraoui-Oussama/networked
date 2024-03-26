package com.fsb.networked.dto;

import java.time.LocalDate;

public class EntrepriseDTO {
    private String entrepriseName;
    private String entrepriseSector;
    private String entrepriseSize;
    private String entrepriseFounders;
    private String entrepriseWebsite;
    private String entrepriseLocation;
    private LocalDate entrepriseDateOfFoundation;
    private String entrepriseLogoPath;
    private String password;
    private String email;

    public EntrepriseDTO(String entrepriseName, String entrepriseSector, String entrepriseSize, String entrepriseFounders, String entrepriseWebsite, String entrepriseLocation, LocalDate entrepriseDateOfFoundation, String entrepriseLogoPath, String password, String email) {
        this.entrepriseName = entrepriseName;
        this.entrepriseSector = entrepriseSector;
        this.entrepriseSize = entrepriseSize;
        this.entrepriseFounders = entrepriseFounders;
        this.entrepriseWebsite = entrepriseWebsite;
        this.entrepriseLocation = entrepriseLocation;
        this.entrepriseDateOfFoundation = entrepriseDateOfFoundation;
        this.entrepriseLogoPath = entrepriseLogoPath;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EntrepriseDTO() {
    }

    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }

    public String getEntrepriseSector() {
        return entrepriseSector;
    }

    public void setEntrepriseSector(String entrepriseSector) {
        this.entrepriseSector = entrepriseSector;
    }

    public String getEntrepriseSize() {
        return entrepriseSize;
    }

    public void setEntrepriseSize(String entrepriseSize) {
        this.entrepriseSize = entrepriseSize;
    }

    public String getEntrepriseFounders() {
        return entrepriseFounders;
    }

    public void setEntrepriseFounders(String entrepriseFounders) {
        this.entrepriseFounders = entrepriseFounders;
    }

    public String getEntrepriseWebsite() {
        return entrepriseWebsite;
    }

    public void setEntrepriseWebsite(String entrepriseWebsite) {
        this.entrepriseWebsite = entrepriseWebsite;
    }

    public String getEntrepriseLocation() {
        return entrepriseLocation;
    }

    public void setEntrepriseLocation(String entrepriseLocation) {
        this.entrepriseLocation = entrepriseLocation;
    }

    public LocalDate getEntrepriseDateOfFoundation() {
        return entrepriseDateOfFoundation;
    }

    public void setEntrepriseDateOfFoundation(LocalDate entrepriseDateOfFoundation) {
        this.entrepriseDateOfFoundation = entrepriseDateOfFoundation;
    }

    public String getEntrepriseLogoPath() {
        return entrepriseLogoPath;
    }

    public void setEntrepriseLogoPath(String entrepriseLogoPath) {
        this.entrepriseLogoPath = entrepriseLogoPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EntrepriseDTO{" +
                "entrepriseName='" + entrepriseName + '\'' +
                ", entrepriseSector='" + entrepriseSector + '\'' +
                ", entrepriseSize='" + entrepriseSize + '\'' +
                ", entrepriseFounders='" + entrepriseFounders + '\'' +
                ", entrepriseWebsite='" + entrepriseWebsite + '\'' +
                ", entrepriseLocation='" + entrepriseLocation + '\'' +
                ", entrepriseDateOfFoundation=" + entrepriseDateOfFoundation +
                ", entrepriseLogoPath='" + entrepriseLogoPath + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
