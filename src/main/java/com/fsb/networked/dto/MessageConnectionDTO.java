package com.fsb.networked.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class MessageConnectionDTO {
    private String connectionImgSrc;
    private String connectionName;
    private String lastMessageBody;
    private LocalDate lastMessageDate;
    private LocalTime lastMessageTime;

    public MessageConnectionDTO(String connectionImgSrc, String connectionName, String lastMessageBody, LocalDate lastMessageDate, LocalTime lastMessageTime) {
        this.connectionImgSrc = connectionImgSrc;
        this.connectionName = connectionName;
        this.lastMessageBody = lastMessageBody;
        this.lastMessageDate = lastMessageDate;
        this.lastMessageTime = lastMessageTime;
    }
    public MessageConnectionDTO() {
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

    public String getLastMessageBody() {
        return lastMessageBody;
    }

    public void setLastMessageBody(String lastMessageBody) {
        this.lastMessageBody = lastMessageBody;
    }

    public LocalDate getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(LocalDate lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public LocalTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageConnectionDTO that = (MessageConnectionDTO) o;
        return Objects.equals(connectionImgSrc, that.connectionImgSrc) && Objects.equals(connectionName, that.connectionName) && Objects.equals(lastMessageBody, that.lastMessageBody) && Objects.equals(lastMessageDate, that.lastMessageDate) && Objects.equals(lastMessageTime, that.lastMessageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionImgSrc, connectionName, lastMessageBody, lastMessageDate, lastMessageTime);
    }

    @Override
    public String toString() {
        return "MessageConnection{" +
                "connectionImgSrc='" + connectionImgSrc + '\'' +
                ", connectionName='" + connectionName + '\'' +
                ", lastMessageBody='" + lastMessageBody + '\'' +
                ", lastMessageDate=" + lastMessageDate +
                ", lastMessageTime=" + lastMessageTime +
                '}';
    }
}
