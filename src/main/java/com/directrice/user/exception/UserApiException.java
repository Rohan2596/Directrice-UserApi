package com.directrice.user.exception;

public class UserApiException extends RuntimeException {

    public ExceptionTypes exceptionTypes;
    public UserApiException(ExceptionTypes exceptionTypes){
        this.exceptionTypes=exceptionTypes;
    }
    public enum ExceptionTypes{
        PASSWORD_INCORRECT("Invalid Password."),
        USER_ALREADY_PRESENT("User Already present"),
        UNAUTHORIZED("Unauthorized User"),
        ALREADY_KYC_INITIALIZE("KYCDetails Already initialize."),
        INVALID_KYC_ID("Invalid KYCDetails Id.");

        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage=errorMessage;
        }
    }
}
