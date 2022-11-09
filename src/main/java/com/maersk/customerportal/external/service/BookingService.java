package com.maersk.customerportal.external.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.maersk.customerportal.dto.BookingDto;
import com.maersk.customerportal.dto.ContainerData;

import reactor.core.publisher.Mono;

@Service
public class BookingService {

	WebClient webClient = WebClient.create("https://www.maersk.com/api/bookings");
	
	//calling an external service to check if the space exists
	public Mono<BookingDto> checkContainer(ContainerData requestData) {
		/* if the timestamp is null, create a new pojo and send the pojo in response to third party */
		return webClient.post()
		        .uri("/checkAvailable")
		        .body(Mono.just(requestData), ContainerData.class)
		        .retrieve()
		        .bodyToMono(BookingDto.class)
		        .doOnError(ex -> System.out.println(ex.getMessage()))
		        .onErrorResume(WebClientResponseException.class, ex -> {
		        	if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
		        		BookingDto dto = new BookingDto();
		        		dto.setAvailableSpace(null);
		                return Mono.just(dto);
		            }				
		        	return Mono.error(ex);	            
		        });

	}

}
