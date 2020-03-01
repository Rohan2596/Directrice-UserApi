package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserDTO extends LoginDTO {

    @NotNull(message = "UserName length should be min 6 and max 8.")
    @NotEmpty(message = "UserName length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "UserName length should be min 6 and max 8.")
    private String userName;

    public UserDTO(String emailId, String password, String userName) {
        super(emailId, password);
        this.userName = userName;
    }
}
