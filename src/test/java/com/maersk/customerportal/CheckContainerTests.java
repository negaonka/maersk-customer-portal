package com.maersk.customerportal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.maersk.customerportal.controllers.CustomerPortalController;
import com.maersk.customerportal.dto.ContainerData;
import com.maersk.customerportal.dto.ResponseData;
import com.maersk.customerportal.external.service.BookingService;
import com.maersk.customerportal.repository.BookingRepository;
import com.maersk.customerportal.service.CustomerPortalService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerPortalController.class)
@Import(CustomerPortalService.class)
@AutoConfigureWebTestClient
public class CheckContainerTests {
	@MockBean
	BookingRepository repository;

	@MockBean
	BookingService service;

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCheckContainer() {
		ContainerData booking = new ContainerData();
		booking.setContainerSize(10);
		booking.setContainerType("DRY");
		booking.setDestination("Singapore");
		booking.setOrigin("Sothhampton");
		booking.setQuantity(100);

		webClient.post().uri("/api/bookings/container").body(Mono.just(booking), ContainerData.class).exchange()
				.expectStatus().is2xxSuccessful().expectHeader().contentType(MediaType.APPLICATION_JSON)
				.returnResult(ResponseData.class).getResponseBody();

	}

}
