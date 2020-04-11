package com.directrice.user.entity;

import com.directrice.user.enumeration.KYCStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
public class KYCDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID kycId;
    private String userId;
    private String aadharCardNo;
    private String panCardNo;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private LocalDateTime updatedTimeStamp;
    private String status;

    public KYCDetails(String userId) {
        this.userId = userId;
        this.aadharCardNo = "";
        this.panCardNo = "";
        this.status = KYCStatus.CREATED.name();
    }

    public KYCDetails() {
    }
}