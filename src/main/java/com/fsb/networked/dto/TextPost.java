package com.fsb.networked.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TextPost {
    private String originalPosterName;
    private LocalDate publicationDate;
    private LocalTime publicationTime;
    private String postText;
    private int numberOfLikes;
    private int numberOfComments;
    private String opImgSrc;

    public TextPost(String originalPosterName, LocalDate publicationDate, LocalTime publicationTime, String postText, int numberOfReactions, int numberOfComments, String opImgSrc) {
        this.originalPosterName = originalPosterName;
        this.publicationDate = publicationDate;
        this.publicationTime = publicationTime;
        this.postText = postText;
        this.numberOfLikes = numberOfReactions;
        this.numberOfComments = numberOfComments;
        this.opImgSrc = opImgSrc;
    }

    public TextPost() {
    }

    public String getOriginalPosterName() {
        return originalPosterName;
    }

    public void setOriginalPosterName(String originalPosterName) {
        this.originalPosterName = originalPosterName;
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

    public String getOpImgSrc() {
        return opImgSrc;
    }

    public void setOpImgSrc(String opImgSrc) {
        this.opImgSrc = opImgSrc;
    }

    @Override
    public String toString() {
        return "Post{" +
                "originalPosterName='" + originalPosterName + '\'' +
                ", publicationDate=" + publicationDate +
                ", postText='" + postText + '\'' +
                ", numberOfReactions=" + numberOfLikes +
                ", numberOfComments=" + numberOfComments +
                ", opImgSrc='" + opImgSrc + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextPost textPost = (TextPost) o;
        return numberOfLikes == textPost.numberOfLikes && numberOfComments == textPost.numberOfComments && Objects.equals(originalPosterName, textPost.originalPosterName) && Objects.equals(publicationDate, textPost.publicationDate) && Objects.equals(publicationTime, textPost.publicationTime) && Objects.equals(postText, textPost.postText) && Objects.equals(opImgSrc, textPost.opImgSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPosterName, publicationDate, publicationTime, postText, numberOfLikes, numberOfComments, opImgSrc);
    }
}
