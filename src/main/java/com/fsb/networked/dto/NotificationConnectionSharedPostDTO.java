package com.fsb.networked.dto;

import java.time.LocalDate;
import java.util.Objects;

public class NotificationConnectionSharedPostDTO {
    private String connectionImgSrc;
    private String connectionName;
    private String postContentStart;
    private LocalDate postShareDate;

    public NotificationConnectionSharedPostDTO(String connectionImgSrc, String connectionName, String postContentStart, LocalDate postShareDate) {
        this.connectionImgSrc = connectionImgSrc;
        this.connectionName = connectionName;
        this.postContentStart = postContentStart;
        this.postShareDate = postShareDate;
    }
    public NotificationConnectionSharedPostDTO() {
    }

    public String getConnectionImgSrc() {
        return connectionImgSrc;
    }

    public void setConnectionImgSrc(String connectionImgSrc) {
        this.connectionImgSrc = connectionImgSrc;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getPostContentStart() {
        return postContentStart;
    }

    public void setPostContentStart(String postContentStart) {
        this.postContentStart = postContentStart;
    }

    public LocalDate getPostShareDate() {
        return postShareDate;
    }

    public void setPostShareDate(LocalDate postShareDate) {
        this.postShareDate = postShareDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationConnectionSharedPostDTO that = (NotificationConnectionSharedPostDTO) o;
        return Objects.equals(connectionImgSrc, that.connectionImgSrc) && Objects.equals(connectionName, that.connectionName) && Objects.equals(postContentStart, that.postContentStart) && Objects.equals(postShareDate, that.postShareDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionImgSrc, connectionName, postContentStart, postShareDate);
    }

    @Override
    public String toString() {
        return "SharePostNotification{" +
                "connectionImgSrc='" + connectionImgSrc + '\'' +
                ", connectionName='" + connectionName + '\'' +
                ", postContentStart='" + postContentStart + '\'' +
                ", postShareDate=" + postShareDate +
                '}';
    }
}
