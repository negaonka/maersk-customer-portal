package com.maersk.customerportal.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maersk.customerportal.validator.ValidContainer;
import com.maersk.customerportal.validator.ValueOfEnum;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ValidContainer
public class ContainerData {
	
    private Integer containerSize;
	
    // add enum validation
    @ValueOfEnum(enumClass = ContainerType.class)
	private String containerType;
	
    @Size(min = 5, max = 20, message="{origin.size.requirement}")
    private String origin;
    
    @Size(min = 5, max=20, message = "{destination.size.requirement}")
    private String destination;
    
    @Min(value = 1, message = "{quantity.min.requirement}")
    @Max(value = 100, message = "{quantity.max.requirement}")
    private Integer quantity;
    
    private String timestamp;
    
    static enum ContainerType {
    	DRY, REEFER;
    }

}
