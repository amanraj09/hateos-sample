package com.fincity.assignment.security.controller;

import com.fincity.assignment.security.request.AuthRequestDTO;
import com.fincity.assignment.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("verify")
    public ResponseEntity verify(@RequestBody @Valid AuthRequestDTO payload) {
        return ResponseEntity.ok(authService.verify(payload));
    }
}
