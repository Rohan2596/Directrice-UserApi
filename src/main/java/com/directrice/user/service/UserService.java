package com.directrice.user.service;


import com.directrice.user.dto.*;
import com.directrice.user.entity.User;

import java.util.List;

public interface UserService {

    String addUser(UserDTO userDTO);
    String authenticate(LoginDTO loginDTO);
    UserSummary getUser(String token);
    List<User> getAll();
    String forgotPassword(String token,ForgotPasswordDTO forgotPasswordDTO);
    String resetPassword(String token,ResetPasswordDTO resetPasswordDTO);
    Boolean verify(String token);
}
