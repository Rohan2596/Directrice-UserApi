package com.directrice.user.controller;

import com.directrice.user.dto.*;
import com.directrice.user.response.Response;
import com.directrice.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
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
        String register= userService.addUser(userDTO);
        return new ResponseEntity<Response>(new Response("User Successfully Added", register),
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
        UserSummary userSummary=userService.getUser(token);
        return new ResponseEntity<Response>(new Response("User Found.", userSummary),
                HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getUserDetailsAll(){
        return new ResponseEntity<Response>(
                new Response("Users List.",
                userService.getAll()),
                HttpStatus.FOUND);
    }

    @PutMapping("reset_Password/{token}")
    public ResponseEntity<Response> resetPassword(@PathVariable String token,
                                                  @RequestBody @Valid ResetPasswordDTO resetPasswordDTO,
                                                    BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
        String result=userService.resetPassword(token,resetPasswordDTO);
        return new ResponseEntity<Response>(new Response("Password Updated Successfully.", result),
                HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Response> forgotPassword(@RequestHeader String token,
                                                   @RequestBody @Valid ForgotPasswordDTO forgotPasswordDTO,
                                                   BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
      String result=userService.forgotPassword(token,forgotPasswordDTO);
        return new ResponseEntity<Response>(new Response("Forgot Password Started.", result),
                HttpStatus.OK);
    }

    @GetMapping("confirm_verification/{token}")
    public ResponseEntity<Response> verify(@PathVariable String token){
        Boolean verify=userService.verify(token);

        return new ResponseEntity<Response>(new Response("EmailId Successfully Verified.", verify),
                HttpStatus.OK);

    }


    @GetMapping("/status")
    public ResponseEntity<Response> changeAccountStatus(@RequestHeader String token){
        userService.changeAccountStatus(token);
        return new ResponseEntity<Response>(new Response("Account Created Successfully.",userService.changeAccountStatus(token)),
                HttpStatus.OK);
    }

    @GetMapping("/userId")
    public UserSummary getUserId(@RequestParam String token){
        UserSummary userSummary=userService.getUser(token);
        return userSummary;
    }




}
