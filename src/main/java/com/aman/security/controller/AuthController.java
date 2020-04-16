package com.aman.security.controller;

import com.aman.common.exception.EntityAlreadyExistsException;
import com.aman.security.component.AccountService;
import com.aman.security.request.AccountCreateRequestDTO;
import com.aman.security.request.AuthRequestDTO;
import com.aman.security.service.AuthService;
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

    @Autowired
    private AccountService accountService;

    @PostMapping("verify")
    public ResponseEntity verify(@RequestBody @Valid AuthRequestDTO payload) {
        return ResponseEntity.ok(authService.verify(payload));
    }

    @PostMapping("register")
    public ResponseEntity createAccount(@RequestBody @Valid AccountCreateRequestDTO payload) throws EntityAlreadyExistsException {
        return ResponseEntity.ok(accountService.create(payload));
    }
}
