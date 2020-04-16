package com.aman.car.controller;

import com.aman.car.request.AddCarRequestDTO;
import com.aman.car.request.UpdateCarRequestDTO;
import com.aman.car.response.CarResponseDTO;
import com.aman.car.service.CarService;
import com.aman.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("")
    public ResponseEntity addCar(@RequestBody @Valid AddCarRequestDTO payload) {
        return ResponseEntity.ok(carService.addCar(payload));
    }

    @GetMapping("")
    public ResponseEntity getAllCars(@RequestParam(value = "searchText", required = false) String searchText,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "page", required = false) Integer page) {
        List<CarResponseDTO> carResponseDTOS = carService.getAllCars(searchText, limit, page);
        if(!CollectionUtils.isEmpty(carResponseDTOS)) {
            carResponseDTOS = carResponseDTOS.stream().map(carResponseDTO -> {
                try {
                    carResponseDTO.add(
                            linkTo(
                                    methodOn(
                                            this.getClass()
                                    ).getCar(carResponseDTO.getId())
                            ).withRel("car")
                    );
                    carResponseDTO.add(
                            linkTo(
                                    methodOn(
                                            this.getClass()
                                    ).getAllCars(searchText, limit, page + 1)
                            ).withSelfRel()
                    );
                } catch (EntityNotFoundException e) {

                }
                return carResponseDTO;
            }).collect(Collectors.toList());
        }
        return ResponseEntity.ok(carResponseDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity getCar(@PathVariable Integer id) throws EntityNotFoundException {
        CarResponseDTO carResponseDTO = carService.getCar(id);
        carResponseDTO.add(linkTo(methodOn(this.getClass()).getCar(id)).withSelfRel());
        carResponseDTO.add(linkTo(methodOn(this.getClass()).getAllCars("red", 10, 0)).withRel("car"));
        return ResponseEntity.ok(carResponseDTO);
    }

    @PutMapping("{carId}")
    public ResponseEntity updateCar(@PathVariable("carId") Integer id, @RequestBody UpdateCarRequestDTO payload)
            throws EntityNotFoundException {
        CarResponseDTO carResponseDTO = carService.updateCar(id, payload);
        carResponseDTO.add(linkTo(methodOn(this.getClass()).getCar(id)).withSelfRel());
        return ResponseEntity.ok(carResponseDTO);
    }

    @DeleteMapping("{carId}")
    public ResponseEntity deleteCar(@PathVariable("carId") Integer id)
            throws EntityNotFoundException {
        return ResponseEntity.ok(carService.deleteCar(id));
    }

}
