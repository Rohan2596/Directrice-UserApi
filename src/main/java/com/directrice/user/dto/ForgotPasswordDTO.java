package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ForgotPasswordDTO  implements Serializable {

    @NotEmpty(message = "Password length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "Password length should be min 6 and max 8.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
