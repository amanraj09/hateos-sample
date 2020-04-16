package com.fincity.assignment.car.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(String carName, String manufacturerName, String manufacturingYear, String model, String color) {
        String query = "INSERT INTO car (" +
                "name, " +
                "manufacturer_name, " +
                "manufacturing_year, " +
                "model, " +
                "color, " +
                "created_at, " +
                "update_at ) " +
                "values " +
                "(?, ?, ?, ?, ?, CURDATE(), CURDATE())";
        return jdbcTemplate.update(query, carName, manufacturerName, manufacturingYear, model, color);

    }
}
