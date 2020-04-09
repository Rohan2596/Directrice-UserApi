package com.directrice.user.enumeration;

public enum KYCStatus {


    CREATED("Documents Validation process initialized."),
    VALIDATION_ASKED("Documents validation Asked."),
    VALIDATED("Documents validated properly."),
    REFUSED("Documents validation refused.");

    KYCStatus(String kycMessage) {
        this.message=kycMessage;
    }
    private String message;
}
