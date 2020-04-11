package com.directrice.user.controller;

import com.directrice.user.dto.KycDTO;
import com.directrice.user.dto.UserDTO;
import com.directrice.user.response.Response;
import com.directrice.user.service.KYCServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/directrice/user/kyc")
public class KYCController {

    @Autowired
    KYCServiceImp kycServiceImp;

    @PostMapping
    public ResponseEntity<Response> createKycDocuments(@RequestHeader String token) {

        return new ResponseEntity<Response>(new Response("KYCDetails process initialized.",kycServiceImp.createKYCDocuments(token)),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Response> updatedKycDocuments(@RequestHeader String token,
                                                        @RequestBody @Valid KycDTO kycDTO,
                                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),""),
                    HttpStatus.BAD_REQUEST);
       String updateKYC= kycServiceImp.updateKYCDocuments(token,kycDTO);
        return new ResponseEntity<Response>(new Response("KYCDetails process initialized.",updateKYC),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response> getUserKycDocuments(@RequestHeader String token,
                                                        @RequestParam String kycId) {
        return new ResponseEntity<Response>(new Response("KYCDetails process initialized.",kycServiceImp.getUserDocuments(token,kycId)),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getUserAllKycDocuments() {
        return new ResponseEntity<Response>(new Response("KYCDetails process initialized.",kycServiceImp.getAllUserDocuments()),
                HttpStatus.CREATED);
    }

}
