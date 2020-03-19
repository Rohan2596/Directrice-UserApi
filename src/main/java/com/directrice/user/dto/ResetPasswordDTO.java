package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class ResetPasswordDTO {

    @NotNull(message = "Password length should be min 6 and max 8.")
    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String password;

    @NotNull(message = "Password length should be min 6 and max 8.")
    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String confirmPassword;
}
