package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ForgotPasswordDTO  implements Serializable {

    @Email(message = "Invalid email-id in username.")
    @NotEmpty(message = "EmailId should not be empty.")
    @NotNull(message = "EmailId should not be empty.")
    private String emailId;



}
