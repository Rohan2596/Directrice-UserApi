package com.directrice.user.dto;

import lombok.Data;
@Data
public class UserSummary {

    private String emailId;
    private String name;

    public UserSummary(String emailID, String name) {
        this.emailId=emailID;
        this.name=name;
    }
}
