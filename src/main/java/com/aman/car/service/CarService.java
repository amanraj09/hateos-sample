package com.aman.car.service;

import com.aman.car.constant.CarConstant;
import com.aman.car.model.Car;
import com.aman.car.repository.CarRepository;
import com.aman.car.request.AddCarRequestDTO;
import com.aman.car.request.UpdateCarRequestDTO;
import com.aman.car.response.CarResponseDTO;
import com.aman.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Car newCar = carRepository.getCarById(newCarId);
        return CarResponseDTO.builder()
                .id(newCar.getId())
                .name(newCar.getName())
                .manufacturerName(newCar.getManufacturerName())
                .manufacturingYear(newCar.getManufacturingYear())
                .model(newCar.getModel())
                .color(newCar.getColor())
                .build();
    }

    public CarResponseDTO getCar(Integer id) throws EntityNotFoundException {
        Car car = carRepository.getCarById(id);
        if(car == null) {
            throw new EntityNotFoundException(
                    CarConstant.ApiFailureMessage.CAR_NOT_FOUND,
                    CarConstant.SystemLogMessages.CAR_NOT_FOUND,
                    id);
        }
        return CarResponseDTO.builder()
                .id(car.getId())
                .name(car.getName())
                .manufacturerName(car.getManufacturerName())
                .manufacturingYear(car.getManufacturingYear())
                .model(car.getModel())
                .color(car.getColor())
                .build();
    }

    public List<CarResponseDTO> getAllCars(String searchText,
                                           Integer limit,
                                           Integer page) {

        limit = limit == null ? CarConstant.ApiDefaultConstants.DEFAULT_LIMIT : limit;
        page = page == null ? CarConstant.ApiDefaultConstants.DEFAULT_PAGE : page;
        List<Car> cars = carRepository.searchCar(limit, page , searchText);
        if(CollectionUtils.isEmpty(cars)) {
            return Arrays.asList();
        }

        return cars.stream()
                .map(
                        car -> CarResponseDTO.builder()
                                .id(car.getId())
                                .name(car.getName())
                                .manufacturerName(car.getManufacturerName())
                                .manufacturingYear(car.getManufacturingYear())
                                .model(car.getModel())
                                .color(car.getColor())
                                .build()
                ).collect(Collectors.toList());
    }

    public CarResponseDTO updateCar(Integer id, UpdateCarRequestDTO payload) throws EntityNotFoundException {
        Car existingCar = carRepository.getCarById(id);
        if(existingCar == null) {
            throw new EntityNotFoundException(
                    CarConstant.ApiFailureMessage.CAR_NOT_FOUND,
                    CarConstant.SystemLogMessages.CAR_NOT_FOUND,
                    id);
        }

        Boolean isCarUpdated = carRepository.update(existingCar, payload);
        if(isCarUpdated) {
            existingCar = carRepository.getCarById(id);
            return CarResponseDTO.builder()
                    .id(existingCar.getId())
                    .name(existingCar.getName())
                    .manufacturerName(existingCar.getManufacturerName())
                    .manufacturingYear(existingCar.getManufacturingYear())
                    .model(existingCar.getModel())
                    .color(existingCar.getColor())
                    .build();
        }
        return CarResponseDTO.builder().build();
    }

    public Boolean deleteCar(Integer id) throws EntityNotFoundException {
        Car existingCar = carRepository.getCarById(id);
        if(existingCar == null) {
            throw new EntityNotFoundException(
                    CarConstant.ApiFailureMessage.CAR_NOT_FOUND,
                    CarConstant.SystemLogMessages.CAR_NOT_FOUND,
                    id);
        }

        return carRepository.deleteById(id);

    }
}
