package com.aman.car.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.aman.car.constant.CarConstant.ApiFailureMessage;

@Getter
@Setter
public class AddCarRequestDTO {

    @NotBlank(message = ApiFailureMessage.BLANK_CAR_NAME)
    private String name;

    @NotBlank(message = ApiFailureMessage.BLANK_MANUFACTURER_NAME)
    private String manufacturerName;

    @NotBlank(message = ApiFailureMessage.BLANK_MANUFACTURE_YEAR)
    private String manufacturingYear;

    @NotBlank(message = ApiFailureMessage.BLANK_CAR_MODEL)
    private String model;

    @NotBlank(message = ApiFailureMessage.BLANK_CAR_COLOR)
    private String color;
}
