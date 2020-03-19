package com.directrice.user.exception;

public class UserApiException extends RuntimeException {

    public ExceptionTypes exceptionTypes;
    public UserApiException(ExceptionTypes exceptionTypes){
        this.exceptionTypes=exceptionTypes;
    }
    public enum ExceptionTypes{
        PASSWORD_INCORRECT("Invalid Password."),
        USER_ALREADY_PRESENT("User Already present"),
        UNAUTHORIZED("Unauthorized User")

        ;

        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage=errorMessage;
        }
    }
}
