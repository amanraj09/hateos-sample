package com.fincity.assignment.car.service;

import com.fincity.assignment.car.repository.CarRepository;
import com.fincity.assignment.car.request.AddCarRequestDTO;
import com.fincity.assignment.car.response.CarResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarResponseDTO addCar(AddCarRequestDTO payload) {
        int newCarId = carRepository.add(payload.getName(),
                payload.getManufacturerName(),
                payload.getManufacturingYear(),
                payload.getModel(),
                payload.getColor());
        return new CarResponseDTO();
    }
}
