package com.fsb.networked.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ImagePostDTO extends TextPostDTO {

    private String attachmentFileSrc;
    public ImagePostDTO(String originalPosterName, LocalDate publicationDate, LocalTime publicationTime, String postText, int numberOfReactions, int numberOfComments, String opImgSrc, String attachementImgSrc) {
        super(originalPosterName, publicationDate,publicationTime, postText, numberOfReactions, numberOfComments, opImgSrc);
        this.attachmentFileSrc = attachementImgSrc;
    }

    public ImagePostDTO() {
    }

    public String getAttachmentFileSrc() {
        return attachmentFileSrc;
    }

    public void setAttachmentFileSrc(String attachmentFileSrc) {
        this.attachmentFileSrc = attachmentFileSrc;
    }

    @Override
    public String toString() {

        return "Image Post : " + super.toString() + " , {" +
                "attachmentFileSrc='" + attachmentFileSrc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ImagePostDTO mediaPost = (ImagePostDTO) o;
        return Objects.equals(attachmentFileSrc, mediaPost.attachmentFileSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attachmentFileSrc);
    }
}
