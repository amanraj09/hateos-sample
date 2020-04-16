package com.aman.car.util;

import com.aman.car.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        return Car.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .manufacturerName(resultSet.getString("manufacturer_name"))
                .manufacturingYear(resultSet.getString("manufacturing_year"))
                .model(resultSet.getString("model"))
                .color(resultSet.getString("color"))
                .build();
    }
}
