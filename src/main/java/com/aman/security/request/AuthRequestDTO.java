package com.aman.security.request;

import com.aman.security.constant.SecurityConstant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class AuthRequestDTO {

    @Email
    private String email;

    @NotBlank(message = SecurityConstant.ApiFailureMessage.BLANK_PASSWORD)
    private String password;
}
