package com.aman.security.request;

import com.aman.security.constant.SecurityConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountCreateRequestDTO {

    @Email
    private String email;

    @NotBlank(message = SecurityConstant.ApiFailureMessage.BLANK_PASSWORD)
    private String password;

    @NotBlank(message = SecurityConstant.ApiFailureMessage.BLANK_NAME)
    private String name;
}
