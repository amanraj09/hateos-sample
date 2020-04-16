package com.fincity.assignment.car.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDTO {
    private Long id;

    private String name;

    private String manufacturerName;

    private String manufacturingYear;

    private String model;

    private String color;
}
