package com.directrice.user.exception;

import com.directrice.user.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserApiGlobalException {
    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<Response> handleCMSException(UserApiException userApiException){
        return new ResponseEntity<Response>(new Response(userApiException.exceptionTypes.errorMessage,""),
                HttpStatus.BAD_REQUEST);
    }
}
