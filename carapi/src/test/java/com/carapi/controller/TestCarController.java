package com.carapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.carapi.model.Car;
import com.carapi.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CarController.class)
public class TestCarController {
	
	@MockBean
	CarService carService;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSaveCarDetails() throws JsonProcessingException, Exception {
		Car car = new Car(2l,"Maruthi","Suzuki","12","Good");
		when(carService.saveCarDetails(car)).thenReturn(car);
		mvc.perform(post("/carapi/saveCar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(car))).andDo(print());
	}
	
	@Test
	public void testGetCarByID() throws Exception {
		Car car = new Car(2l,"Maruthi","Suzuki","12","Good");
		when(carService.getCarById(2l)).thenReturn(Optional.of(car));
		mvc.perform(get("/carapi/getAllcars/{id}",2l)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void tesrGetCarByName() throws Exception {
		String name = "Maruthi";
		Car car1 = new Car(1l,"Maruthi","Suzuki","12","Good");
		Car car2 = new Car(2l,"Maruthi","800","12","Good");
		Car car3 = new Car(3l," BMW","GLS","8","Good");
		List<Car> allCars = new ArrayList<Car>();
		allCars.add(car1);
		allCars.add(car2);
		allCars.add(car3);
		when(carService.getCarByName(name)).thenReturn(allCars);
		mvc.perform(get("/carapi//getCarsByName/{name}",name).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void testGetCarByIDNotFound() throws Exception {
		Long id = 2l;
		when(carService.getCarById(id)).thenReturn(Optional.empty());
		mvc.perform(get("/carapi/getAllcars/{id}",id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetAllCars() throws Exception {
	    Car car = new Car(2L, "BMW", "GLS", "12", "Good");
	    Car car2 = new Car(3L, "Maruthi", "Suzuki", "12", "Good");
	    List<Car> allCars = new ArrayList<>();
	    allCars.add(car);
	    allCars.add(car2);
	    
	    when(carService.getAllCars()).thenReturn(allCars);

	    mvc.perform(get("/carapi/getAllcars")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andDo(print());
	}
	
	@Test
	public void testGetAllCars_Empty() throws Exception {
	    List<Car> allCars = new ArrayList<>();
	    when(carService.getAllCars()).thenReturn(allCars);

	    mvc.perform(get("/carapi/getAllcars")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andDo(print());
	}

}
