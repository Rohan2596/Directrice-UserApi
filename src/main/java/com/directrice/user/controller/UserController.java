package com.directrice.user.controller;

import com.directrice.user.dto.*;
import com.directrice.user.entity.User;
import com.directrice.user.response.Response;
import com.directrice.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/directrice/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid  @RequestBody UserDTO userDTO,
                                             BindingResult  bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
        userService.addUser(userDTO);
        return new ResponseEntity<Response>(new Response("User Successfully Added", ""),
                HttpStatus.CREATED);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody @Valid LoginDTO loginDTO,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
        String token=userService.authenticate(loginDTO);


        return new ResponseEntity<Response>(new Response("User Successfully Authenticated.", token),
                HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<Response> getUserDetails(@RequestHeader String token){
        User user=userService.getUser(token);
        return new ResponseEntity<Response>(new Response("User Found.", user),
                HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getUserDetailsAll(){
        userService.getAll();
        return new ResponseEntity<Response>(new Response("Users List.", ""),
                HttpStatus.FOUND);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Response> resetPassword(@RequestHeader String token,
                                                  @RequestBody @Valid ResetPasswordDTO resetPasswordDTO,
                                                  BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
        String result=userService.resetPassword(resetPasswordDTO);
        return new ResponseEntity<Response>(new Response("Password Updated Successfully.", ""),
                HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Response> forgotPassword(@RequestHeader String token,
                                                   @RequestBody @Valid ForgotPasswordDTO forgotPasswordDTO,
                                                   BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
      String result=userService.forgotPassword(forgotPasswordDTO);
        return new ResponseEntity<Response>(new Response("Password Updated Successfully.", result),
                HttpStatus.OK);
    }

    @GetMapping("confirm_verification/{token}")
    public ResponseEntity<Response> verify(@PathVariable String token){
        Boolean verfiy=userService.verify(token);

        return new ResponseEntity<Response>(new Response("EmailId Successfully Verified.", verfiy),
                HttpStatus.OK);

    }



}
