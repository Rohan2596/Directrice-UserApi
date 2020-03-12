package com.directrice.user.entity;

import com.directrice.user.dto.UserDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;



@Document
public class User  {

    @Id
    private String userId;
    private String name;
    @DBRef
    private UserCredentials userCredentials;
    private LocalDateTime createdTimeStamp=LocalDateTime.now();
    private LocalDateTime updatedTimeStamp=LocalDateTime.now();

    public User(String userName,UserCredentials userCredentials) {
       this.name=userName;
       this.userCredentials= userCredentials;

    }
}
