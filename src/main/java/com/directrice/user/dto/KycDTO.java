package com.directrice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class KycDTO {


    @NotEmpty(message = "KYCDetails Id cannot be Empty.")
    @NotNull(message = "KYCDetails Id cannot be Empty.")
    private String kycId;
    @NotEmpty(message = "Aadhar Card No cannot be Empty.")
    @NotNull(message = "Aadhar Card No cannot be Empty.")
    @Pattern(regexp = "^\\d{4}\\s\\d{4}\\s\\d{4}$")
    private String aadharCardNo;
    @NotEmpty(message = "PanCard No cannot be Empty.")
    @NotNull(message = "PanCard No canont be Empty.")
    @Pattern(regexp = "[A-Z]{5}\\d{4}[A-Z]{1}")
    private String panCardNo;
}
