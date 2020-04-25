package com.directrice.user.entity;



import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



@Entity
@Data
public class User  {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private  UUID userId;
    private String name;
    private String emailId;
    private String mobileNumber;
    private Boolean isVerify =false;
    private Boolean isAccount=false;

    @OneToOne
    private UserCredentials userCredentials;
    private LocalDateTime createdTimeStamp=LocalDateTime.now();
    private LocalDateTime updatedTimeStamp=LocalDateTime.now();

    public User(String userName,String emailId,UserCredentials userCredentials,String mobileNumber) {
       this.name=userName;
       this.emailId=emailId;
       this.userCredentials= userCredentials;
        this.mobileNumber=mobileNumber;
    }


    public User() {
    }
}
