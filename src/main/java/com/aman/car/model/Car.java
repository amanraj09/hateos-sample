package com.aman.car.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer id;

    private String name;

    private String manufacturerName;

    private String manufacturingYear;

    private String model;

    private String color;

    private Date createdAt;

    private Date updatedAt;
}
