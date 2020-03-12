package com.directrice.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.UUID;


@Document
public class UserCredentials {

    @Id
    private String userCredentialsId=UUID.randomUUID().toString();
    private String emailID;
    private String password;


    public UserCredentials(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }
}
