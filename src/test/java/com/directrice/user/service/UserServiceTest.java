package com.directrice.user.service;

import com.directrice.user.dto.*;
import com.directrice.user.entity.User;
import com.directrice.user.entity.UserCredentials;
import com.directrice.user.exception.UserApiException;
import com.directrice.user.respository.UserCredentialsRepository;
import com.directrice.user.respository.UserRepository;
import com.directrice.user.utility.TokenGenerators;
import com.directrice.user.utility.mail.SendEmail;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTest {

    @Mock
    public UserRepository userRepository;

    @Mock
    public UserCredentialsRepository userCredentialsRepository;

    @Mock
    private TokenGenerators tokenGenerators;

    @Mock
    private SendEmail sendEmail;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDTO userDTO;
    private LoginDTO loginDTO;
    private ForgotPasswordDTO forgotPasswordDTO;
    private ResetPasswordDTO resetPasswordDTO;
    private User user;
    private UserCredentials userCredentials;
    private String token;
    private Optional<User> optionalUser;
    private Optional<UserCredentials> optionalUserCredentials;
    private UUID uuid;
    private UserSummary userSummary;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        this.loginDTO=new LoginDTO("rohan.kadam@directrice.com","DIRECT@1");
        this.userDTO=new UserDTO("rohan.kadam@directrice.com","DIRECT@1","Direct@1","7894561230");
        this.optionalUserCredentials=Optional.of(new UserCredentials("rohan.kadam@directrice.com","DIRECT@1"));
        this.optionalUser=Optional.of(new User("Direct@1","rohan.kadam@directrice.com",this.optionalUserCredentials.get(),"7894561230"));
        this.token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJJRCI6IjdjYTg3YzUyLTZlNDktNDZjMS05N2M1LTE4NDJhNDcxYmZhZSJ9.IkpRi8" +
                "_SmBvW2meewK2IMLihA09VyYRPpsYrsUNr6UA";
        this.uuid=UUID.randomUUID();
        this.userCredentials=new UserCredentials("rohan.kadam@directrice.com","Direct@1");
        this.user=new User("Direct@1","rohan.kadam@directrice.com",this.userCredentials,"7894561230");
        this.userSummary=new UserSummary("rohan.kadam@directrice.com","Direct@1","rohankadam",true);
        this.forgotPasswordDTO=new ForgotPasswordDTO("rohan.kadam@directrice.com");
        this.resetPasswordDTO=new ResetPasswordDTO("Direct@1","Direct@1");
    }
//****************AUTHENTICATED*************//

    @Test
    void givenValidLoginDetails_WhenAuthenticated_shouldReturnToken() {
        Mockito.when(passwordEncoder.matches(any(),any())).thenReturn(true);
        Mockito.when(userRepository.findByEmailId(this.loginDTO.getEmailId())).thenReturn(this.optionalUser);
        Mockito.when(userCredentialsRepository.findByEmailID(this.loginDTO.getEmailId())).thenReturn(this.optionalUserCredentials);
        Mockito.when(tokenGenerators.generateToken(any())).thenReturn(this.token);
        String token=userService.authenticate(this.loginDTO);
        System.out.println("TOKEN"+token);
        Assert.assertEquals(this.token,token);
    }

    @Test
    void givenInValidLoginDetails_IncorrectEmailId_WhenAuthenticated_shouldReturnValidResponse() {
        try{
        String token=userService.authenticate(this.loginDTO);
        System.out.println("TOKEN"+token);
    }catch (UserApiException userApiException){
            Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);
        }
    }

    @Test
    void givenInValidLoginDetails_IncorrectPassword_WhenAuthenticated_shouldReturnValidResponse() {
        try{
            this.optionalUserCredentials.get().setPassword("bdmfbsd");
            Mockito.when(userRepository.findByEmailId(this.loginDTO.getEmailId())).thenReturn(this.optionalUser);
            Mockito.when(userCredentialsRepository.findByEmailID(this.loginDTO.getEmailId())).thenReturn(this.optionalUserCredentials);
            String token=userService.authenticate(this.loginDTO);
            System.out.println("TOKEN"+token);
        }catch (UserApiException userApiException){
            Assert.assertEquals("Invalid Password.",userApiException.exceptionTypes.errorMessage);
        }
    }

    //***************Add User*****************//
    @Test
    void givenValidUserDTO_WhenAdded_shouldReturnCorrectResponse() {
        Mockito.when(userCredentialsRepository.save(any())).thenReturn(this.userCredentials);
        Mockito.when(passwordEncoder.encode(any())).thenReturn(this.userCredentials.getPassword());
        Mockito.when(userRepository.save(any())).thenReturn(this.user);
        Mockito.when(tokenGenerators.decodeToken(any())).thenReturn(this.uuid);
        Mockito.when(userRepository.findByEmailId(this.loginDTO.getEmailId())).thenReturn(Optional.empty());
        Mockito.when(userCredentialsRepository.findByEmailID(this.loginDTO.getEmailId())).thenReturn(Optional.empty());
        String addUser=userService.addUser(this.userDTO);
        Assert.assertEquals("rohan.kadam@directrice.com",addUser);

    }

    @Test
    void givenInValidUserDTO_WhenAdded_shouldReturnCorrectResponse() {
        try{
            Mockito.when(userCredentialsRepository.save(any())).thenReturn(this.userCredentials);
            Mockito.when(userRepository.save(any())).thenReturn(this.user);

            Mockito.when(userRepository.findByEmailId(this.userDTO.getEmailId())).thenReturn(this.optionalUser);
            userService.addUser(this.userDTO);

        }catch (UserApiException userApiException){
            Assert.assertEquals("User Already present",userApiException.exceptionTypes.errorMessage);
        }
    }

    //***************Forgot Password**********//

    @Test
    void givenValidForgotPassword_WhenVerified_shouldReturnCorrectResponse(){
        Mockito.when(tokenGenerators.decodeToken(any())).thenReturn(this.uuid);
        Mockito.when(userRepository.findByEmailId(any())).thenReturn(this.optionalUser);
        String message=userService.forgotPassword(this.token,this.forgotPasswordDTO);
        Assert.assertEquals("Email Sent to Registered one.",message);
    }

    @Test
    void givenInValidForgotPassword_WhenVerified_shouldReturnCorrectResponse(){
        try{
        userService.forgotPassword(this.token,this.forgotPasswordDTO);

    }catch (UserApiException userApiException){
            Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);
        }
    }



    //***************Get All User*************//

    @Test
    void givenValid_whenVerified_shouldReturnListofUsers(){
        List<User> userList=new ArrayList<>();
        userList.add(this.user);
        userList.add(this.user);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> newUserList=userRepository.findAll();
        Assert.assertEquals(userList.size(),newUserList.size());
    }
    //**************Get User Details*********//

    @Test
    void givenValid_whenVerified_shouldReturnValidUser(){
        Mockito.when(tokenGenerators.decodeToken(any())).thenReturn(this.uuid);
        Mockito.when(userRepository.findById(any())).thenReturn(this.optionalUser);
        UserSummary userSummary=userService.getUser(this.token);
        Assert.assertEquals(this.optionalUser.get().getEmailId(),userSummary.getEmailId());
    }

    @Test
    void givenInValidToken_whenVerified_shouldReturnValidUser(){
        try{
        UserSummary userSummary=userService.getUser(this.token);
        Assert.assertEquals(this.optionalUser.get().getEmailId(),userSummary.getEmailId());
        }catch (UserApiException userApiException){
        Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);
        }
    }

    //*****************Reset password***************************//

    @Test
    void givenValidResetPasswordDto_whenVerified_shouldReturnValidResponse(){
        Mockito.when(userRepository.findById(any())).thenReturn(this.optionalUser);
        String result=userService.resetPassword(this.token,this.resetPasswordDTO);
        Assert.assertEquals("rohan.kadam@directrice.com",result);
    }

    @Test
    void givenInValidResetPasswordDto_whenVerified_shouldReturnValidResponse(){
       try{
            userService.resetPassword(this.token,this.resetPasswordDTO);
        }catch (UserApiException userApiException){
            Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);

       }
    }

    //*********************Verify User****************************************//

    @Test
    void givenValidVerify_whenVerified_shouldReturnValidResponse(){
        Mockito.when(userRepository.findById(any())).thenReturn(this.optionalUser);
        Boolean result=userService.verify(this.token);
        Assert.assertTrue(result);
    }

    @Test
    void givenInValidVerify_whenVerified_shouldReturnValidResponse(){
        try {
            userService.verify(this.token);
        }catch (UserApiException userApiException){
            Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);
        }

    }

    //*****************Changing Status of Account*******************//

    @Test
    void givenValidToken_whenVerified_shouldChange_shouldReturnValidResponse(){
        Mockito.when(userRepository.findById(any())).thenReturn(this.optionalUser);
        Boolean result=userService.changeAccountStatus(this.token);
        Assert.assertTrue(result);

    }
    @Test
    void givenInValidToken_whenVerified_ShouldNotChange_shouldReturnValidResponse(){
        try {
            userService.changeAccountStatus(this.token);
        }catch (UserApiException userApiException){
            Assert.assertEquals("Unauthorized User",userApiException.exceptionTypes.errorMessage);
        }

    }


}
