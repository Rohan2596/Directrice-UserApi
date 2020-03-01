package com.directrice.user.controller;

import com.directrice.user.dto.LoginDTO;
import com.directrice.user.dto.ResponseDTO;
import com.directrice.user.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/directrice/user")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid UserDTO userDTO,
                                                BindingResult  bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Successfully Added", ""),
                HttpStatus.CREATED);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody @Valid LoginDTO loginDTO,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Successfully Authenticated.", ""),
                HttpStatus.OK);
    }



}
