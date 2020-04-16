package com.aman.security.component;

import com.aman.common.exception.EntityAlreadyExistsException;
import com.aman.security.constant.SecurityConstant;
import com.aman.security.model.Account;
import com.aman.security.repository.AccountRepository;
import com.aman.security.request.AccountCreateRequestDTO;
import com.aman.security.request.AuthRequestDTO;
import com.aman.security.response.AuthResponseDTO;
import com.aman.security.service.AuthService;
import com.aman.security.util.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.getAccountByEmail(username);
    }

    public AuthResponseDTO create(AccountCreateRequestDTO payload) throws EntityAlreadyExistsException {
        if(accountRepository.getAccountByEmail(payload.getEmail()) != null) {
            throw new EntityAlreadyExistsException(
                    SecurityConstant.ApiFailureMessage.EMAIL_EXIST,
                    SecurityConstant.SystemLogMessages.EMAIL_EXIST,
                    payload.getEmail()
            );
        }
        accountRepository.create(payload.getEmail(),
                passwordEncoder.encode(payload.getPassword()),
                payload.getName());
        Account account =  accountRepository.getAccountByEmail(payload.getEmail());
        return authService.verify(AuthRequestDTO.builder()
                .email(account.getEmail())
                .password(payload.getPassword())
                .build());

    }
}
