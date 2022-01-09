package com.springboot.app.carrent.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.springboot.app.carrent.exception.RentedCarException;
import com.springboot.app.carrent.exception.ResourceNotFoundException;
import com.springboot.app.carrent.model.Car;
import com.springboot.app.carrent.repository.CarRepository;
import com.springboot.app.carrent.service.CarService;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	

	@Override
	public Car saveCar(Car car) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return carRepository.findAll();
	}

	@Override
	public Car getCarById(long id) {
		// TODO Auto-generated method stub
		return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "Id", id));
	}

	@Override
	public Car updateCar(Car car, long id) {
		// TODO Auto-generated method stub
		Car existingCar = carRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Car", "Id", id));
		existingCar.setCustomerName(car.getCustomerName());
		existingCar.setModel(car.getModel());
		existingCar.setRentedFrom(car.getRentedFrom());
		existingCar.setRentedTo(car.getRentedTo());
		// save existing car to DB
		carRepository.save(existingCar);
		return existingCar;
	}

	@Override
	public void deleteCar(long id) {
		// TODO Auto-generated method stub
		carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "Id", id));
		carRepository.deleteById(id);
	}

	@Override
	public Car rentCar( Map<Object, Object> fields, long id) {
		// TODO Auto-generated method stub
        Car existingCar = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "Id", id));
        if(!(existingCar.getCustomerName() == null && existingCar.getRentedFrom()== null&& existingCar.getRentedTo()== null))
			throw new RentedCarException();
        fields.forEach((k, v) -> {
            // use reflection to get field k on manager and set it to value v
             Field field = ReflectionUtils.findField(Car.class, (String) k);
             field.setAccessible(true);
             ReflectionUtils.setField(field, existingCar, v);
         });
        carRepository.save(existingCar);
        return existingCar;
	}

	@Override
	public List<Car> getAllAvailableCars() {
		// TODO Auto-generated method stub
		return carRepository.findByModelIsNullAndRentedFromIsNullAndRentedToIsNull();
	}
	
	
	
	private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Scheduled(cron="0 0 * * * *")
	public void freeCars ()
	{
		List<Car> cars = carRepository.findAllByRentedToBefore(new Date());
		for(Car car:cars)
		{
			car.setCustomerName(null);
			car.setRentedFrom(null);
			car.setRentedTo(null);
			carRepository.save(car);
		}
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
	

}
