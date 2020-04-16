package com.fincity.assignment.car.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Car {
    private Long id;

    private String name;

    private String manufacturerName;

    private String manufacturingYear;

    private String model;

    private String color;

    private Date createdAt;

    private Date updatedAt;
}
