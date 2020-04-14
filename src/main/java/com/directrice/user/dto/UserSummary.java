package com.directrice.user.dto;

import lombok.Data;
@Data
public class UserSummary {

    private String userId;
    private String emailId;
    private String name;

    public UserSummary(String userId,String emailID, String name) {
        this.userId=userId;
        this.emailId=emailID;
        this.name=name;
    }




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserSummary{" +
                "userId='" + userId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
