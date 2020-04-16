package com.aman.car.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
public class CarResponseDTO extends RepresentationModel<CarResponseDTO> {
    private Integer id;

    private String name;

    private String manufacturerName;

    private String manufacturingYear;

    private String model;

    private String color;
}
