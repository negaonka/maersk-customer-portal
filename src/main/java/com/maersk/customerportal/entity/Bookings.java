package com.maersk.customerportal.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*to create a bookings table*/
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("bookings")
public class Bookings {
	
	//TODO: need to check an alternate way to increement the count since its get reset after server restarts. 
	//Also not a good approach to increement the count
	//get latest value from db and increement the booking_ref
	private static Integer count = 957000001;
	@PrimaryKey()
    @Builder.Default
    private String booking_ref = String.valueOf(count++);
	
	private Integer container_size;
	
	private String container_type;
	
	private String origin;
	
	private String destination;
	
	private Integer quantity;
	
	private String timestamp;	

}
