package com.fincity.assignment.security.service;

import com.fincity.assignment.security.component.JwtTokenProvider;
import com.fincity.assignment.security.request.AuthRequestDTO;
import com.fincity.assignment.security.response.AuthResponseDTO;
import com.fincity.assignment.security.util.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthMapper authMapper;

    public AuthResponseDTO verify(AuthRequestDTO payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(),
                payload.getPassword()));
        String token = jwtTokenProvider.generateToken(payload.getEmail());
        return authMapper.doMap(payload.getEmail(), token);
    }
}
