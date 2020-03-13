package com.directrice.user.service;

import com.directrice.user.dto.ForgotPasswordDTO;
import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.ResetPasswordDTO;
import com.directrice.user.dto.UserDTO;
import com.directrice.user.entity.User;

import java.util.List;

public interface UserService {

    User addUser(UserDTO userDTO);
    String authenticate(LoginDTO loginDTO);
    User getUser(String token);
    List<User> getAll();
    String forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
    String resetPassword(ResetPasswordDTO resetPasswordDTO);
    Boolean verify(String token);

}
