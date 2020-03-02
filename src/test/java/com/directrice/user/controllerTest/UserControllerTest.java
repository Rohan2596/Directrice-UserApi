package com.directrice.user.controllerTest;

import com.directrice.user.dto.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    private UserDTO userDTO;
    private LoginDTO loginDTO;
    private ResetPasswordDTO resetPasswordDTO;
    private ForgotPasswordDTO forgotPasswordDTO;

    @BeforeEach
    void setUp() {

    }

    //  CONTROLLER TEST CASES FOR REGISTRATION

    @Test
    void givenValidUserDTO_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam662@gmail.com","Rohan123","Rohan123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(201,result.getResponse().getStatus());
        Assert.assertEquals("User Successfully Added",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidUserName_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123",null);

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }
    @Test
    void givenInValidUserName_MIN_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123","ROHAN");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidUserName_MAX_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@abc.com","Abcd@123","ROHANKADAM");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("UserName length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidEmailId_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO(null,"Abcd@123","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidEmailId_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam","Abcd@123","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Invalid email-id in username.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }


    @Test
    void givenInValidEmailId_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("","Abcd@123","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("EmailId should not be empty.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidPassword_NULL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com",null,"ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidPassword_EMPTY_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidPassword_MIN_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","Rohan","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidPassword_MAX_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.userDTO=new UserDTO("rohankadam@ac.com","ROHAN1234","ROHAN123");

        String userDTO=new Gson().toJson(this.userDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/register")
                .content(userDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

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
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenValidToken_ALL_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/directrice/user/getAll")
                .header("token","token")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(302,result.getResponse().getStatus());
        Assert.assertEquals("Users List.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    ///Controller Test cases for Reset Password


    @Test
    void givenValidResetPasswordDTO_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN123","ROHAN123");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Password Updated Successfully.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenINvalidOldPassword_Null_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO(null,"ROHAN123");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidOldPassword_Min_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohan","ROHAN123");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidOldPassword_Max_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("Rohanaerwr","ROHAN123");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidOldPassword_Empty_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("","ROHAN123");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidNewPassword_Empty_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN123","");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidNewPassword_Null_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN123",null);
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidNewPassword_Min_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN123","ROHAN");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInvalidNewPassword_Max_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.resetPasswordDTO=new ResetPasswordDTO("ROHAN123","ROHAN12343436");
        String resetPasswordDTO=new Gson().toJson(this.resetPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/resetPassword")
                .header("token","token")
                .content(resetPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }


    @Test
    void givenValidForgotPassword_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO();
        this.forgotPasswordDTO.setPassword("ROHAN123");
        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .header("token","token")
                .content(forgotPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(200,result.getResponse().getStatus());
        Assert.assertEquals("Password Updated Successfully.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidForgotPassword_Null_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO();
        this.forgotPasswordDTO.setPassword(null);
        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .header("token","token")
                .content(forgotPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidForgotPassword_Empty_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO();
        this.forgotPasswordDTO.setPassword("");
        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .header("token","token")
                .content(forgotPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }

    @Test
    void givenInValidForgotPassword_Min_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO();
        this.forgotPasswordDTO.setPassword("Rohan");
        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .header("token","token")
                .content(forgotPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }
    @Test
    void givenInValidForgotPassword_Max_WhenAuthenticated_shouldReturnValidResponse() throws Exception {
        this.forgotPasswordDTO=new ForgotPasswordDTO();
        this.forgotPasswordDTO.setPassword("Rohan12344");
        String forgotPasswordDTO=new Gson().toJson(this.forgotPasswordDTO);
        MvcResult result = this.mockMvc.perform(post("/directrice/user/forgotPassword")
                .header("token","token")
                .content(forgotPasswordDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assert.assertEquals(400,result.getResponse().getStatus());
        Assert.assertEquals("Password length should be min 6 and max 8.",
                new Gson().fromJson(result.getResponse().getContentAsString(), ResponseDTO.class).message);

    }




}
