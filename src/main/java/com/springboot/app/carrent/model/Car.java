package com.springboot.app.carrent.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.springboot.app.carrent.annotation.RangeCheck;

import lombok.Data;

@Data
@Entity
@Table(name="car")
@RangeCheck(message = "Rent from must be before rent to")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Please provide a model for the car")
	@NotNull(message = "Please provide a model for the car")
	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "rented_from")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date rentedFrom;
	
	@Column(name = "rented_to")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date rentedTo;
}
