package com.carapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.carapi.exception.CarNotFoundException;
import com.carapi.model.Car;
import com.carapi.repository.CarRepository;

@SpringBootTest
public class CarServiceTest {
	
	@MockBean
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Test
	public void testSaveCarDetails() {
		Car car = new Car(2l,"Maruthi","Suzuki","12","Good");
		when(carRepository.save(car)).thenReturn(car);
		assertEquals(car, carService.saveCarDetails(car));
	}
	
	@Test
	public void testGetAllCarDetails() {
		List<Car> allCars = Arrays.asList(new Car(2l,"BMW","GLS","12","Good"),new Car(3l,"Maruthi","Suzuki","12","Good"));
		when(carRepository.findAll()).thenReturn(allCars);
		assertEquals(allCars.size(), carService.getAllCars().size());
	}
	
	@Test
	public void testGetCarById() {
		Car car = new Car(2l,"Maruthi","Suzuki","12","Good");
		Optional<Car> optionalCar = Optional.of(car);
		when(carRepository.findById(2l)).thenReturn(optionalCar);
		assertEquals(optionalCar, carService.getCarById(2l));
	}
	
	@Test
	public void testDeleteCarById() {
	    Long id = 2L;
	    when(carRepository.existsById(id)).thenReturn(false);

	    assertThrows(CarNotFoundException.class, () -> carService.deleteCarById(id));
	    verify(carRepository, times(1)).findById(id);
	    verify(carRepository, never()).deleteById(id);
	}




}
