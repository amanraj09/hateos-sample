package com.fincity.assignment.security.util;

import com.fincity.assignment.security.response.AuthResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponseDTO doMap(String email, String token) {
        return AuthResponseDTO.builder()
                .email(email)
                .token(token)
                .build();
    }

}
