package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KycDTO {

    private String kycId;
    private String aadhaarCardNo;
    private String panCardNo;
    private LocalDateTime updatedTimeStamp=LocalDateTime.now();
}
