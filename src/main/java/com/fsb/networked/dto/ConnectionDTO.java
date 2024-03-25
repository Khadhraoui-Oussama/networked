package com.fsb.networked.dto;

import java.time.LocalDate;

public class ConnectionDTO {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private LocalDate dateOfConnection;
    private String connectionImage;

    public ConnectionDTO(String emailAddress, String firstName, String lastName, LocalDate dateOfConnection) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfConnection = dateOfConnection;
    }

    public ConnectionDTO() {
    }

    public String getConnectionImage() {
        return connectionImage;
    }

    public void setConnectionImage(String connectionImage) {
        this.connectionImage = connectionImage;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public LocalDate getDateOfConnection() {
        return dateOfConnection;
    }

    public void setDateOfConnection(LocalDate dateOfConnection) {
        this.dateOfConnection = dateOfConnection;
    }
}
