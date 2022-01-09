package com.springboot.app.carrent.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.carrent.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	List<Car> findByModelIsNullAndRentedFromIsNullAndRentedToIsNull();
	List<Car> findAllByRentedToBefore(Date date);
}
