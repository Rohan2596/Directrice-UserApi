package com.directrice.user.service;

import com.directrice.user.dto.*;
import com.directrice.user.entity.User;
import com.directrice.user.entity.UserCredentials;
import com.directrice.user.exception.UserApiException;
import com.directrice.user.respository.UserCredentialsRepository;
import com.directrice.user.respository.UserRepository;

import com.directrice.user.utility.TokenGenerators;
import com.directrice.user.utility.mail.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private TokenGenerators tokenGenerators;

    @Autowired
    private SendEmail sendEmail;

    @Override
    public User addUser(UserDTO userDTO) {
        Optional<User> optionalUser=userRepository.findByEmailId(userDTO.getEmailId());
        if(!optionalUser.isPresent()) {
            Optional<UserCredentials> optionalUserCredentials = userCredentialsRepository.findByEmailID(userDTO.getEmailId());
            if (optionalUserCredentials.isPresent()) {
                throw new UserApiException(UserApiException.ExceptionTypes.USER_ALREADY_PRESENT);
            }
            UserCredentials userCredentials = new UserCredentials(userDTO.getEmailId(), userDTO.getPassword());
            userCredentialsRepository.save(userCredentials);
            User user = new User(userDTO.getUserName(), userDTO.getEmailId(),userCredentials);
            Email email=new Email(userDTO.getEmailId(),"rohan.kadam@bridgelabz.com","Verification","Registration");
            email.setBody("http://localhost:8081/directrice/user/confirm_verification/"+"token");


            sendEmail.send(email);
            return userRepository.save(user);
        }
        throw  new UserApiException(UserApiException.ExceptionTypes.USER_ALREADY_PRESENT);

    }



    @Override
    public String authenticate(LoginDTO loginDTO) {
       Optional<User> optionalUser=userRepository.findByEmailId(loginDTO.getEmailId());
       if(optionalUser.isPresent()) {
           Optional<UserCredentials> optionalUserCredentials = userCredentialsRepository.findByEmailID(loginDTO.getEmailId());
           if (optionalUserCredentials.isPresent()){
               if(!optionalUserCredentials.get().getPassword().equals(loginDTO.getPassword())){
                   throw new UserApiException(UserApiException.ExceptionTypes.PASSWORD_INCORRECT);
               }
               return tokenGenerators.generateToken(optionalUser.get().getUserId());
           }
           throw new UserApiException(UserApiException.ExceptionTypes.UNAUTHORIZED);
       }
       throw  new UserApiException(UserApiException.ExceptionTypes.UNAUTHORIZED);
    }

    @Override
    public UserSummary getUser(String token) {
        UUID userID= tokenGenerators.decodeToken(token);
        User user= this.verifyUser(userID);
        user.getName();
        user.getUserCredentials().getEmailID();
        UserSummary userSummary=new UserSummary(user.getUserCredentials().getEmailID(),user.getName());
        return userSummary;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public String forgotPassword(String token,ForgotPasswordDTO forgotPasswordDTO) {
        UUID userId=tokenGenerators.decodeToken(token);
        Optional<User> optionalUser=userRepository.findByEmailId(forgotPasswordDTO.getEmailId());

        if(optionalUser.isPresent()){
            token=tokenGenerators.generateToken(optionalUser.get().getUserId());
            Email email=new Email(optionalUser.get().getEmailId(),"rohan.kadam@bridgelabz.com","ForgotPassword","Registration");
            email.setBody("http://localhost:8081/directrice/user/reset_Password/"+token);

            sendEmail.send(email);
            return "Email Sent to Registered one.";
        }
        throw  new UserApiException(UserApiException.ExceptionTypes.UNAUTHORIZED);
    }

    @Override
    public String resetPassword(String token, ResetPasswordDTO resetPasswordDTO) {
        UUID userId=tokenGenerators.decodeToken(token);
        User user=this.verifyUser(userId);
        if(!resetPasswordDTO.getConfirmPassword().equals(resetPasswordDTO.getPassword()))
        {
            throw  new UserApiException(UserApiException.ExceptionTypes.PASSWORD_INCORRECT);
        }
        user.getUserCredentials().setPassword(resetPasswordDTO.getConfirmPassword());
        userCredentialsRepository.save(user.getUserCredentials());
        return "Updated Successfully Forgot password.";
    }

    @Override
    public Boolean verify(String token) {
        System.out.println("Token:-"+token);
        UUID userID=tokenGenerators.decodeToken(token);
        User user=this.verifyUser(userID);
        user.setIsVerify(true);
        userRepository.save(user);
        return true;
    }

     private User verifyUser(UUID userId){
         Optional<User>optionalUser=userRepository.findById(userId);
         if (optionalUser.isPresent()){
             return optionalUser.get();
         }
         throw  new UserApiException(UserApiException.ExceptionTypes.UNAUTHORIZED);
     }




}
