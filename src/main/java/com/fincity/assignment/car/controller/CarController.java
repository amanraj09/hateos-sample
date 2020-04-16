package com.fincity.assignment.car.controller;

import com.fincity.assignment.car.request.AddCarRequestDTO;
import com.fincity.assignment.car.response.CarResponseDTO;
import com.fincity.assignment.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("")
    public CarResponseDTO addCar(@RequestBody @Valid AddCarRequestDTO payload) {
        return carService.addCar(payload);
    }
}
