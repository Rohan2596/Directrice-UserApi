package com.directrice.user.dto;


import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@NoArgsConstructor
public class UserDTO extends LoginDTO {

    @NotNull(message = "UserName length should be min 6 and max 8.")
    @NotEmpty(message = "UserName length should be min 6 and max 8.")
    @Length(min = 6, max = 8, message = "UserName length should be min 6 and max 8.")
    private String userName;

    @NotNull(message = "MobileNumber length should be 10.")
    @NotEmpty(message = "MobileNumber length should be 10.")
    @Length(min = 10, max = 10, message = "MobileNumber length should be 10.")
    private String mobileNumber;

    public UserDTO(String emailId, String password, String userName,String mobileNumber) {
        super(emailId, password);
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
