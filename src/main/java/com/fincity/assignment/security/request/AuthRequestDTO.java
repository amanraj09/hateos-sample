package com.fincity.assignment.security.request;

import com.fincity.assignment.security.constant.SecurityConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthRequestDTO {

    @NotBlank(message = SecurityConstant.ApiFailureMessage.BLANK_EMAIL)
    private String email;

    @NotBlank(message = SecurityConstant.ApiFailureMessage.BLANK_PASSWORD)
    private String password;
}
