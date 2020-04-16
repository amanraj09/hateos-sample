package com.aman.car.repository;

import com.aman.car.model.Car;
import com.aman.car.request.UpdateCarRequestDTO;
import com.aman.car.util.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getBaseQuery(int limit, int page) {
        return String.format("select * from car limit %d offset %d", limit, page * limit);
    }

    public String getSearchQuery(int limit, int page) {
        String query = "select * from car where name = ? " +
                "OR manufacturer_name = ? " +
                "OR model = ? " +
                "OR manufacturing_year = ? " +
                "OR color = ? " +
                "limit %d offset %d";
        return String.format(query + "", limit, page * limit);
    }

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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(query, new String[] {"id"});
                    ps.setString(1, carName);
                    ps.setString(2, manufacturerName);
                    ps.setString(3, manufacturingYear);
                    ps.setString(4, model);
                    ps.setString(5, color);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Car getCarById(Integer id) {

        String query = "SELECT * FROM car WHERE id = ?";
        return jdbcTemplate.query(query, new Object[] { id }, new CarMapper()).stream().findFirst().orElse(null);
    }

    public List<Car> getCarByQuery(String query, String searchText) {
        return jdbcTemplate.query(
                query,
                new Object[] {
                        searchText, searchText, searchText, searchText, searchText
                },
                new CarMapper()
        );
    }

    public Boolean update(Car existingCar, UpdateCarRequestDTO payload) {
        boolean isUpdateRequested = false;
        List<Object> queryArguments = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("update car set ");
        if(!StringUtils.isEmpty(payload.getName())) {
            queryBuilder.append("name = ?").append(", ");
            queryArguments.add(payload.getName());
            isUpdateRequested = true;
        }
        if(!StringUtils.isEmpty(payload.getManufacturerName())) {
            queryBuilder.append("manufacturer_name = ?").append(", ");
            queryArguments.add(payload.getManufacturerName());
            isUpdateRequested = true;
        }
        if(!StringUtils.isEmpty(payload.getManufacturingYear())) {
            queryBuilder.append("manufacturing_year = ?").append(", ");
            queryArguments.add(payload.getManufacturingYear());
            isUpdateRequested = true;
        }
        if(!StringUtils.isEmpty(payload.getModel())) {
            queryBuilder.append("model = ?").append(", ");
            queryArguments.add(payload.getModel());
            isUpdateRequested = true;
        }
        if(!StringUtils.isEmpty(payload.getColor())) {
            queryBuilder.append("color = ?").append(", ");
            queryArguments.add(payload.getColor());
            isUpdateRequested = true;
        }
        if(isUpdateRequested) {
            queryBuilder.append("update_at = CURDATE() where id = ?");
            queryArguments.add(existingCar.getId());
            return jdbcTemplate.update(
                    String.valueOf(queryBuilder),
                    queryArguments.stream().toArray(Object[]::new)
            ) > 0;
        }
        return false;
    }

    public Boolean deleteById(Integer id) {
        String query = "delete from car where id = ?";
        return jdbcTemplate.update(query, id) > 0;
    }
}
