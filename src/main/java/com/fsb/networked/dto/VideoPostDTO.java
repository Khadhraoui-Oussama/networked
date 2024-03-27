package com.fsb.networked.dto;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;

public class VideoPostDTO extends TextPostDTO {

    private Blob attachmentFile;
    public VideoPostDTO(String originalPosterName,int posterID, LocalDateTime publicationDateTime, String postText, int numberOfReactions, int numberOfComments, Blob opImgSrc, Blob attachementImgSrc) {
        super(originalPosterName,posterID, publicationDateTime, postText, numberOfReactions, numberOfComments, opImgSrc);
        this.attachmentFile = attachementImgSrc;
    }

    public VideoPostDTO() {
    }

    public Blob getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(Blob attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    @Override
    public String toString() {
        return "Video " + super.toString() + " , {" +
                "attachmentFileSrc='" + attachmentFile +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attachmentFile);
    }
}
