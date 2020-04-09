package com.directrice.user.dto;


import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
@Data
public class LoginDTO {

    @javax.validation.constraints.Email(message = "Invalid email-id in username.")
    @NotEmpty(message = "EmailId should not be empty.")
    @NotNull(message = "EmailId should not be empty.")
    private String emailId;

    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String password;

    public LoginDTO(@javax.validation.constraints.Email(message = "Invalid email-id in username.") @NotEmpty(message = "EmailId should not be empty.") @NotNull(message = "EmailId should not be empty.") String emailId, @NotEmpty(message = "Password length should be min 6 and max 8.") @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.") String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public LoginDTO() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
