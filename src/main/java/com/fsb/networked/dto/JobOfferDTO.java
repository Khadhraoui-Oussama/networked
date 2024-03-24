package com.fsb.networked.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class JobOfferDTO {
    private String company;
    private LocalDate publicationDate;
    private LocalTime publicationTime;
    private String description;
    private int numberOfApplicants;
    private String logoImgSrc;

    public JobOfferDTO(String company, LocalDate publicationDate, LocalTime publicationTime, String description, int numberOfApplicants, String logoImgSrc) {
        this.company = company;
        this.publicationDate = publicationDate;
        this.publicationTime = publicationTime;
        this.description = description;
        this.numberOfApplicants = numberOfApplicants;
        this.logoImgSrc = logoImgSrc;
    }

    public JobOfferDTO() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalTime getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(LocalTime publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfApplicants() {
        return numberOfApplicants;
    }

    public void setNumberOfApplicants(int numberOfApplicants) {
        this.numberOfApplicants = numberOfApplicants;
    }

    public String getLogoImgSrc() {
        return logoImgSrc;
    }

    public void setLogoImgSrc(String logoImgSrc) {
        this.logoImgSrc = logoImgSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOfferDTO jobOffer = (JobOfferDTO) o;
        return numberOfApplicants == jobOffer.numberOfApplicants && Objects.equals(company, jobOffer.company) && Objects.equals(publicationDate, jobOffer.publicationDate) && Objects.equals(publicationTime, jobOffer.publicationTime) && Objects.equals(description, jobOffer.description) && Objects.equals(logoImgSrc, jobOffer.logoImgSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, publicationDate, publicationTime, description, numberOfApplicants, logoImgSrc);
    }
}
