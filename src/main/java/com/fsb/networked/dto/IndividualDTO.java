package com.fsb.networked.dto;

import java.time.LocalDate;

public class IndividualDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private byte[] profile_picture;
    private byte[] video_resume;
    private byte[] pdf_resume;
    private String country;
    private boolean isAdmin;

    public IndividualDTO(int id, String firstName, String lastName, String email, LocalDate birthDate, byte[] profile_picture, byte[] video_resume, byte[] pdf_resume, String country, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.profile_picture = profile_picture;
        this.video_resume = video_resume;
        this.pdf_resume = pdf_resume;
        this.country = country;
        this.isAdmin = isAdmin;
    }

    public byte[] getPdf_resume() {
        return pdf_resume;
    }

    public void setPdf_resume(byte[] pdf_resume) {
        this.pdf_resume = pdf_resume;
    }

    public IndividualDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(byte[] profile_picture) {
        this.profile_picture = profile_picture;
    }

    public byte[] getVideo_resume() {
        return video_resume;
    }

    public void setVideo_resume(byte[] video_resume) {
        this.video_resume = video_resume;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
