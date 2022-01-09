package com.springboot.app.carrent.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;




@Target({TYPE,FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = RangeCheckValidator.class)
public @interface RangeCheck {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
