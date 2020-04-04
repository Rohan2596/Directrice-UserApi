package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {

    private String emailTo;
    private String emailFrom;
    private String subject;
    private String body;

}
