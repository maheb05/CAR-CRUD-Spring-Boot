package com.carapi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carapi.model.Car;
import com.carapi.service.CarService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/carapi")
@Transactional
@Slf4j
public class CarController {
	
	private static final Logger log = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	CarService carService;
	
	@PostMapping("/saveCar")
	public Car saveCarDetails(@RequestBody Car car) {
		log.info("inside saveCar Controller");
		return carService.saveCarDetails(car);
	}
	
//	@GetMapping("/getAllcars")
//	public List<Car> getAllCars(){
//		log.info("inside getAllCars Controller");
//	    return carService.getAllCars();
//	}
	
	@GetMapping("/getAllcars")
	public ResponseEntity<List<Car>> getAllCars(){
		return ResponseEntity.ok(carService.getAllCars());
	}

//	@GetMapping("/getAllcars/{id}")
//	public Optional<Car> getCarById(@PathVariable("id") Long carId) {
//		log.info("inside getCarById Controller");
//	    return carService.getCarById(carId);
//	}
	
	@GetMapping("/getAllcars/{id}")
	public ResponseEntity<Optional<Car>> getCarById(@PathVariable("id") Long carId){
		return ResponseEntity.ok(carService.getCarById(carId));
	}
	
	@GetMapping("/getCarsByName/{name}")
	public ResponseEntity<Object> getCarsByName(@PathVariable("name") String carName){
		log.info("inside getCarByName Controller");
		List<Car> carSByName = carService.getCarByName(carName);
		if(carSByName.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(carSByName);
	}
	
	@GetMapping("/getCarsByReview/{review}")
	public List<Car> getCarsByReview(@PathVariable("review") String review) {
		log.info("inside getCarByReview Controller");
	    return carService.getCarsByReview(review);
	}
	
	@GetMapping("/getCarsByMileage/{mileage}")
	public List<Car> getCarsByMileage(@PathVariable("mileage") String mileage){
		log.info("inside getCarByMileage Controller");
		return carService.getCarsByMileage(mileage);
	}
	
	@GetMapping("/getCarsByCarModel/{model}")
	public List<Car> getCarsByCarModel(@PathVariable("model") String carModel){
		log.info("inside getCarByModel Controller");
		List<Car> carsByCarModel = carService.getCarsByCarModel(carModel);
		log.info("returning cars by model");
		return carsByCarModel;
	}
	
	@PutMapping("/updateCar")
	public Car updateCar(@RequestBody Car car) {
		log.info("inside updateCar Controller");
		return carService.updateCar(car);
	}

	@DeleteMapping("/deleteCar/{id}")
	public String deleteById(@PathVariable("id") Long carId) {
		log.info("inside deleteCarById Controller");
	    return carService.deleteCarById(carId);
	}

	@DeleteMapping("/deleteAllCars")
	public String deleteAllCars() {
		log.info("inside deleteAllCars Controller");
	    return carService.deleteAllCars();
	}
	
}
