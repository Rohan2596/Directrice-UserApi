package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
public class ResetPasswordDTO {

    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String oldPassword;

    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String newPassword;
}
