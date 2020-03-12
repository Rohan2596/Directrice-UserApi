package com.directrice.user.service;

import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.UserDTO;
import com.directrice.user.entity.User;
import com.directrice.user.entity.UserCredentials;
import com.directrice.user.respository.UserCredentialsRepository;
import com.directrice.user.respository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public User addUser(UserDTO userDTO) {
        UserCredentials userCredentials=new UserCredentials(userDTO.getEmailId(),userDTO.getPassword());
        userCredentialsRepository.save(userCredentials);
        User user=new User(userDTO.getUserName(),userCredentials);
        return  userRepository.save(user);
    }

    @Override
    public String authenticate(LoginDTO loginDTO) {
        return null;
    }
}
