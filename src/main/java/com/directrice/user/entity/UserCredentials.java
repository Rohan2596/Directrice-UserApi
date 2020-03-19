package com.directrice.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.UUID;


@Entity
@Data
public class UserCredentials {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID userCredentialsId;

    private String emailID;
    private String password;


    public UserCredentials(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public UserCredentials() {
    }
}
