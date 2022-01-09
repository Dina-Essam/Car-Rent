package com.springboot.app.carrent.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.carrent.model.Car;
import com.springboot.app.carrent.service.CarService;

@RestController
@Validated
@RequestMapping("/api/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	// build create Car REST API
	@PostMapping()
	public ResponseEntity<Car> saveCar(@RequestBody @Valid Car Car){
		return new ResponseEntity<Car>(carService.saveCar(Car), HttpStatus.CREATED);
	}
	
	// build get all Cars REST API
	@GetMapping
	public List<Car> getAllCars(){
		return carService.getAllCars();
	}
	
	// build get Car by id REST API
	// http://localhost:8080/api/Cars/1
	@GetMapping("{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") long CarId){
		return new ResponseEntity<Car>(carService.getCarById(CarId), HttpStatus.OK);
	}
	
	// build update Car REST API
	// http://localhost:8080/api/Cars/1
	@PutMapping("{id}")
	public ResponseEntity<Car> updateCar(@PathVariable("id") long id
												  ,@Valid @RequestBody Car Car){
		return new ResponseEntity<Car>(carService.updateCar(Car, id), HttpStatus.OK);
	}
	
	// build delete Car REST API
	// http://localhost:8080/api/Cars/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCar(@PathVariable("id") long id){
		// delete Car from DB
		carService.deleteCar(id);
		return new ResponseEntity<String>("Car deleted successfully!.", HttpStatus.OK);
	}
	
	// build update Car REST API
	// http://localhost:8080/api/Cars/1
	@PatchMapping("/rent/{id}")
	public ResponseEntity<Car> rentCar(@PathVariable("id") long id
												  , @Valid @RequestBody Map<Object, Object> fields){
		return new ResponseEntity<Car>(carService.rentCar(fields, id), HttpStatus.OK);
	}
	
	@GetMapping("/rent/available")
	public List<Car> getAllAvailableCars(){
		return carService.getAllAvailableCars();
	}
	
	
}
