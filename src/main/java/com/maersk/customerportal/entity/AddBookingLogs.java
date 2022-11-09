package com.maersk.customerportal.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*to log all the bookings exception*/
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("add_booking_log")
public class AddBookingLogs {
	
	@PrimaryKey()
    @Builder.Default
    private Integer id = 0;
	
	private String error;
	
	private String booking_ref;
}
	