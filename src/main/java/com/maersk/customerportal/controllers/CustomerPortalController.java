package com.maersk.customerportal.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.customerportal.dto.ContainerData;
import com.maersk.customerportal.dto.ResponseData;
import com.maersk.customerportal.entity.Bookings;
import com.maersk.customerportal.service.CustomerPortalService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/bookings")
public class CustomerPortalController {

	@Autowired
	private CustomerPortalService customerPortalService;
	
	@PostMapping("container")
	public Mono<ResponseData> checkContainer(@Valid @RequestBody ContainerData requestData) {
		return this.customerPortalService.checkContainers(requestData);
	}
	
	@PostMapping("createBooking")
	public Mono<Map<String, String>> createBooking(@Valid @RequestBody ContainerData requestData) {
		return this.customerPortalService.add(requestData);
	}
	
}
