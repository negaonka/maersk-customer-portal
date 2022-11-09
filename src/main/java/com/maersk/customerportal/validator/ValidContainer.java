package com.maersk.customerportal.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = ContainerValidator.class)
public @interface ValidContainer {
    String message() default "{container.size.validate}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}