package com.directrice.user.service;

import com.directrice.user.dto.ForgotPasswordDTO;
import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.ResetPasswordDTO;
import com.directrice.user.dto.UserDTO;
import com.directrice.user.entity.User;
import com.directrice.user.entity.UserCredentials;
import com.directrice.user.exception.UserApiException;
import com.directrice.user.respository.UserCredentialsRepository;
import com.directrice.user.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public User addUser(UserDTO userDTO) {
        Optional<UserCredentials> optionalUserCredentials=userCredentialsRepository.findByEmailID(userDTO.getEmailId());
        if(optionalUserCredentials.isPresent()) {
            throw new UserApiException();

        }
        UserCredentials userCredentials=new UserCredentials(userDTO.getEmailId(),userDTO.getPassword());
        userCredentialsRepository.save(userCredentials);
        User user=new User(userDTO.getUserName(),userCredentials);
        return  userRepository.save(user);

    }

    @Override
    public String authenticate(LoginDTO loginDTO) {
       Optional<UserCredentials> optionalUserCredentials=userCredentialsRepository.findByEmailID(loginDTO.getEmailId());
       if(optionalUserCredentials.isPresent()) return "token";
       throw new UserApiException();
    }

    @Override
    public User getUser(String token) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public String forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        return null;
    }

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return null;
    }

    @Override
    public Boolean verify(String token) {
        return null;
    }


}
