package com.maersk.customerportal.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.maersk.customerportal.dto.ContainerData;

public class ContainerValidator implements ConstraintValidator<ValidContainer, ContainerData> {

    @Override
    public boolean isValid(ContainerData requestData, ConstraintValidatorContext constraintValidatorContext) {
        if(requestData.getContainerSize() == 10 || requestData.getContainerSize() == 20) {
        	return true;
        } else {
        	return false;
        }
    }

}