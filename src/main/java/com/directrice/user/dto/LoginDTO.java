package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@AllArgsConstructor
public class LoginDTO {

    @Email(message = "Invalid email-id in username.")
    @NotEmpty(message = "EmailId should not be empty.")
    @NotNull(message = "EmailId should not be empty.")
    private String emailId;

    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String password;
}
