package com.carapi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carapi.controller.CarController;
import com.carapi.exception.CarNotFoundException;
import com.carapi.model.Car;
import com.carapi.repository.CarRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CarService {
	
	private static final Logger log = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	private CarRepository carRepository;
	
	public Car saveCarDetails(Car car) {
		log.info("inside saveCar Service");
		return carRepository.save(car); 
	}
	
	public List<Car> getAllCars(){
		log.info("inside getAllCars Service");
		List<Car> allCars = carRepository.findAll();
		if(!allCars.isEmpty()) {
			return allCars;
		}else {
			log.error("error occured while fetching all the cars");
			throw new CarNotFoundException("message", "no cars are present");
		} 
	}
	
	public Optional<Car> getCarById(Long carId) {
		log.info("inside getCarById Service");
		Optional<Car> car = carRepository.findById(carId);
		if(car.isPresent()) {
			log.info("retrieving car by id");
			return car;
		}else {
			log.error("exception occured because no car found with that is "+carId);
			throw new CarNotFoundException("message", "no car found with this id "+carId);
		}
		
	}
	
	public Car updateCar(Car updateCar) {
		log.info("inside updateCar Service");
		Optional<Car> car = carRepository.findById(updateCar.getCarId());
		if(car.isPresent()) {
			Car existingCar = car.get();
			existingCar.setCarName(updateCar.getCarName());
			existingCar.setCarModel(updateCar.getCarModel());
			existingCar.setMileage(updateCar.getMileage());
			existingCar.setReview(updateCar.getReview());
			return carRepository.save(existingCar);
		}else {
			log.error("an error occured while updating");
			throw new CarNotFoundException("message", "update unsuccessfully because no car present with this is id "+updateCar.getCarId());
		}
	}
	
	public String deleteCarById(Long carId) {
		log.info("inside deleteCarById Service");
		Optional<Car> car = carRepository.findById(carId);
		if(car.isPresent()) {
			carRepository.deleteById(carId);
			log.info("car deleted successfull using id");
			return "deleted successful";
		}else {
			log.error("exception occured because no car found with that is "+carId);
			throw new CarNotFoundException("message", "delete unsuccessfull because no car found with this is id "+carId);
		}
	}
	
	public String deleteAllCars() {
		log.info("inside deleteAllCar Service");
		long carCount = carRepository.count();
		if(carCount > 0) {
			carRepository.deleteAll();
			log.info("successfully deleted all cars");
			return "Successfully deleted all the Cars";
		}else {
			log.error("exception occured because no car db is empty");
			throw new CarNotFoundException("message", "no cars available");
		}
	}
	
	
	public List<Car> getCarByName(String carName){
		log.info("inside getCarByName Service");
		List<Car> allCarsByName = carRepository.findAll();
		List<Car> cars = allCarsByName.stream()
				.filter(car -> car.getCarName().equalsIgnoreCase(carName))
				.collect(Collectors.toList());
		if(!cars.isEmpty()) {
			log.info("getting car by name");
			return cars;
		}else {
			log.error("an error occured while retrieving car by name");
			throw new CarNotFoundException("Message","no cars found with this name "+carName);
		}
	}
	
	public List<Car> getCarsByReview(String review){
		log.info("inside getCarByReview Service");
		List<Car> allCars = carRepository.findAll();
		Map<Boolean, List<Car>> carsByReview = allCars.stream().collect(Collectors.partitioningBy(car -> car.getReview().equalsIgnoreCase(review)));
		List<Car> carsByRequestedReviewAndRemainingReview = Stream.concat(carsByReview.get(true).stream(), carsByReview.get(false).stream())
	            .collect(Collectors.toList());
		return carsByRequestedReviewAndRemainingReview;
	}
	
	public List<Car> getCarsByMileage(String mileage){
		log.info("inside getCarByMileage Service");
		List<Car> allCars = carRepository.findAll();
		Map<Boolean, List<Car>> carsByMileage = allCars.stream().collect(Collectors.partitioningBy(car -> car.getMileage().equals(mileage)));
		List<Car> carsByRequestedMileageAndRemainingMileage = Stream.concat(carsByMileage.get(true).stream(), carsByMileage.get(false).stream()).collect(Collectors.toList());
		return carsByRequestedMileageAndRemainingMileage;
	}
	
	public List<Car> getCarsByCarModel(String carModel){
		log.info("inside getCarByModel Service");
		List<Car> allCars = carRepository.findAll();
		Map<Boolean, List<Car>> carsByCarModel = allCars.stream().collect(Collectors.partitioningBy(car -> car.getCarModel().equalsIgnoreCase(carModel)));
		List<Car> carsByRequestedModelAndAvailableModels = Stream.concat(carsByCarModel.get(true).stream(), carsByCarModel.get(false).stream()).collect(Collectors.toList());
		return carsByRequestedModelAndAvailableModels;
	}
	
}
