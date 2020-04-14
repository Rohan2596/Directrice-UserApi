package com.directrice.user.controllerTest;

import com.directrice.user.dto.*;
import com.directrice.user.response.Response;
import com.directrice.user.service.UserService;
import com.directrice.user.service.UserServiceImpl;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    private UserDTO userDTO;
    private LoginDTO loginDTO;
    private ResetPasswordDTO resetPasswordDTO;
    private ForgotPasswordDTO forgotPasswordDTO;

    @MockBean
    UserServiceImpl userService;

    //  CONTROLLER TEST CASES FOR REGISTRATION

    @Test
    void givenValidUserDTO_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam662@gmail.com","Rohan123","Rohan123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(201,result.getResponse().getStatus());
        Assert.assertEquals("User Successfully Added",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidUserName_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123",null,"7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }
    @Test
    void givenInValidUserName_MIN_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123","ROHAN","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidUserName_MAX_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123","ROHANKADAM","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidEmailId_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO(null,"Abcd@123","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidEmailId_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam","Abcd@123","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Invalid email-id in username.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }


    @Test
    void givenInValidEmailId_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("","Abcd@123","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidPassword_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com",null,"ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidPassword_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidPassword_MIN_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","Rohan","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidPassword_MAX_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","ROHAN1234","ROHAN123","7894561230");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    //AUTHENTICATION  TEST CASES FOR USERS

    @Test
    void givenValidLoginDetails_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam662@gmail.com","Abcd123");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("User Successfully Authenticated.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginEmailID_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO(null,"Abcd@123");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginEmailID_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("","Abcd@123");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginEmailID_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam@.com","Abcd@123");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Invalid email-id in username.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginPassword_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam@abc.com",null);

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginPassword_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam@abc.com","");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginPassword_MIN_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam@abc.com","rohan");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidLoginPassword_MAX_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.loginDTO=new LoginDTO("rohankadam@abc.com","rohan123456");

        String userDTO=new Gson().toJson(this.loginDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/authenticate")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    //GETTING USER DETAILS

    @Test
    void givenValidToken_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/user/getUser")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(302,result.getResponse().getStatus());
        Assert.assertEquals("User Found.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }



    //Controller Test Cases for Get AlL User
    @Test
    void givenValid_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/user/getAll")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(302,result.getResponse().getStatus());
        Assert.assertEquals("Users List.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    //Verfication user
    @Test
    void givenValidtoken_WhenVerfication_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/user/confirm_verification/{token}","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("EmailId Successfully Verified.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }


    //CONTROLLER TEST CASES FOR FORGOT PASSWORD
    @Test
    void givenValidForgotpassword_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO("rohan.kadam@directrice.com");

        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .content(forgotPasswordDTO)
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Forgot Password Started.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidForgotpassword_NULL_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO(null);

        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .content(forgotPasswordDTO)
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidForgotpassword_EMPTY_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO(null);

        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .content(forgotPasswordDTO)
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidForgotpassword_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO("rohan");

        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .content(forgotPasswordDTO)
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Invalid email-id in username.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    ///Controller Test cases for Reset Password

    @Test
    void givenValidResetDTO_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN@1","ROHAN@1");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Password Updated Successfully.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_password_NULL_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO(null,"ROHAN@1");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_confirmPassword_NULL_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohan@1",null);

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_password_EMPTY_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("","ROHAN@1");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_confirmPassword_EMPTY_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohan@1","");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_password_MIN_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHA","ROHAN@1");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_confirmPassword_MIN_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohan@1","ROHAN");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_password_MAX_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN@133","ROHAN@1");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

    @Test
    void givenInValidResetDTO_confirmPassword_MAX_WhenVerified_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohan@1","ROHAN@123");

        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(put("/directrice/user/reset_Password/{token}","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);

    }

}
