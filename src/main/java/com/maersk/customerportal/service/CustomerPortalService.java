package com.maersk.customerportal.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.customerportal.dto.BookingDto;
import com.maersk.customerportal.dto.ContainerData;
import com.maersk.customerportal.dto.ResponseData;
import com.maersk.customerportal.entity.AddBookingLogs;
import com.maersk.customerportal.entity.Bookings;
import com.maersk.customerportal.external.service.BookingService;
import com.maersk.customerportal.repository.BookingRepository;
import com.maersk.customerportal.util.DateUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPortalService {

	@Autowired
	private BookingService bookingService;

	private final BookingRepository repository;

	private final static String timestampFormat = "yyyy-MM-dd'T'HH:mm:ss";

	public CustomerPortalService(BookingRepository repository) {
		this.repository = repository;
	}

	/*to call the external service and handle the response data*/
	public Mono<ResponseData> checkContainers(ContainerData request) {
		Mono<BookingDto> bookingDto = bookingService.checkContainer(request);
		return bookingDto.flatMap(booking -> {
			final ResponseData responseData = new ResponseData();
			if (booking.getAvailableSpace() != null && booking.getAvailableSpace() > 0)
				responseData.setAvailable(true);
			else
				responseData.setAvailable(false);
			return Mono.just(responseData);
		});

	}

	/* to save a new booking in the booking table */
	public Mono<Map<String, String>> add(ContainerData request) {
		Map<String, String> bookings = new HashMap<>();
		Bookings booking = new Bookings();
		AddBookingLogs log = new AddBookingLogs();
		try {
			// to check if the timestamp is valid, if not display error to the user
			if (DateUtil.validateTimestamp(timestampFormat, request.getTimestamp())) {
				booking.setContainer_size(request.getContainerSize());
				booking.setContainer_type(request.getContainerType());
				booking.setDestination(request.getDestination());
				booking.setOrigin(request.getOrigin());
				booking.setQuantity(request.getQuantity());
				booking.setTimestamp(request.getTimestamp());
				repository.save(booking).log();
				bookings.put("bookingRef", booking.getBooking_ref());
			} else {
				bookings.put("error", "The timestamp format is not valid");

			}
		} catch (Exception e) {
			log.setBooking_ref(booking.getBooking_ref());
			if (e.getMessage() != null) {
				log.setError(e.getMessage());
			} else {
				log.setError("Error saving bookings in the database");
			}
			bookings.put("Internal Server Error", "Sorry there was a problem processing your request");
		}
		return Mono.just(bookings);
	}
}
