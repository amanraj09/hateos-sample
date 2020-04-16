package com.aman.car.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarRequestDTO {
    private String name;

    private String manufacturerName;

    private String manufacturingYear;

    private String model;

    private String color;
}
