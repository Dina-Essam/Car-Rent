package com.springboot.app.carrent.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.springboot.app.carrent.model.Car;

public class RangeCheckValidator implements ConstraintValidator<RangeCheck, Car> {

    @Override
    public void initialize(RangeCheck date) {
        // Nothing here
    }

    @Override
    public boolean isValid(Car car, ConstraintValidatorContext constraintValidatorContext) {
        if (car.getRentedFrom() == null || car.getRentedTo() == null) {
            return true;
        }
        return car.getRentedFrom().before(car.getRentedTo());
    }
}