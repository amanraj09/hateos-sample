package com.fincity.assignment.security.repository;

import com.fincity.assignment.security.model.Account;
import com.fincity.assignment.security.util.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountMapper accountMapper;

    public Account getAccountByEmail(String email) {

        String query = "SELECT * FROM account WHERE email ?";
        return jdbcTemplate.query(query, new AccountMapper()).stream().findFirst().orElse(new Account());
    }
}
