package com.aman.security.repository;

import com.aman.security.model.Account;
import com.aman.security.util.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account getAccountByEmail(String email) {

        String query = "SELECT * FROM account WHERE email = ?";
        return jdbcTemplate.query(query, new Object[] { email }, new AccountMapper()).stream().findFirst().orElse(null);
    }

    public int create(String email, String password, String name) {
        String query = "INSERT INTO account (" +
                "email, " +
                "password, " +
                "name, " +
                "created_at, " +
                "update_at ) " +
                "values " +
                "(?, ?, ?, CURDATE(), CURDATE())";
        return jdbcTemplate.update(query, email, password, name);
    }
}
