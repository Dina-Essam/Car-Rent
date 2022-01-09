package com.springboot.app.carrent.service;

import java.util.List;
import java.util.Map;

import com.springboot.app.carrent.model.Car;

public interface CarService {
	
	Car saveCar(Car Car);
	List<Car> getAllCars();
	Car getCarById(long id);
	Car updateCar(Car Car, long id);
	void deleteCar(long id);
	Car rentCar( Map<Object, Object> fields, long id);
	List<Car> getAllAvailableCars();
}
