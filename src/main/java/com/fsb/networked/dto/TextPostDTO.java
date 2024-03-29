package com.fsb.networked.dto;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;

public class TextPostDTO implements Comparable<TextPostDTO>{
    private String originalPosterName;
    private int posterID;
    private LocalDateTime publicationDateTime;
    private String postText;
    private int numberOfLikes;
    private int numberOfComments;
    private Blob opImgSrc;

    public TextPostDTO(String originalPosterName,int posterID, LocalDateTime publicationDateTime, String postText, int numberOfReactions, int numberOfComments, Blob opImgSrc) {
        this.originalPosterName = originalPosterName;
        this.posterID = posterID;
        this.publicationDateTime = publicationDateTime;
        this.postText = postText;
        this.numberOfLikes = numberOfReactions;
        this.numberOfComments = numberOfComments;
        this.opImgSrc = opImgSrc;
    }

    public TextPostDTO() {
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public String getOriginalPosterName() {
        return originalPosterName;
    }

    public void setOriginalPosterName(String originalPosterName) {
        this.originalPosterName = originalPosterName;
    }

    public LocalDateTime getPublicationDateTime() {
        return this.publicationDateTime;
    }

    public void setPublicationDateTime(LocalDateTime publicationDateTime) {
        this.publicationDateTime = publicationDateTime;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Blob getOpImgSrc() {
        return opImgSrc;
    }

    public void setOpImgSrc(Blob opImgSrc) {
        this.opImgSrc = opImgSrc;
    }

    @Override
    public String toString() {
        return "TextPostDTO{" +
                "originalPosterName='" + originalPosterName + '\'' +
                ", posterID=" + posterID +
                ", publicationDateTime=" + publicationDateTime +
                ", postText='" + postText + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                ", numberOfComments=" + numberOfComments +
                ", opImgSrc=" + opImgSrc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextPostDTO that = (TextPostDTO) o;
        return posterID == that.posterID && numberOfLikes == that.numberOfLikes && numberOfComments == that.numberOfComments && Objects.equals(originalPosterName, that.originalPosterName) && Objects.equals(publicationDateTime, that.publicationDateTime) && Objects.equals(postText, that.postText) && Objects.equals(opImgSrc, that.opImgSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPosterName, posterID, publicationDateTime, postText, numberOfLikes, numberOfComments, opImgSrc);
    }

    //compare to based on publicationDateTime
    @Override
    public int compareTo(TextPostDTO o) {
        //compare based on the publication date and time to load the posts later in the home page based on the same metric
        return this.getPublicationDateTime().compareTo(o.getPublicationDateTime());
    }
}
