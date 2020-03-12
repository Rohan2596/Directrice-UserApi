package com.directrice.user.service;

import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.UserDTO;
import com.directrice.user.entity.User;

public interface UserService {

    User addUser(UserDTO userDTO);
    String authenticate(LoginDTO loginDTO);

}
