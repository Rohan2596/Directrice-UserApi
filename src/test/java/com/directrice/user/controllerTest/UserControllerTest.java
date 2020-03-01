package com.directrice.user.controllerTest;

import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.ResponseDTO;
import com.directrice.user.dto.UserDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    public UserDTO userDTO;
    public LoginDTO loginDTO;

    @BeforeEach
    void setUp() {
        this.loginDTO=new LoginDTO("rohankadam662@gmail.com","Abcd@123");

    }

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













}
