package com.maersk.customerportal.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.maersk.customerportal.entity.Bookings;

@Repository
public interface BookingRepository extends ReactiveCassandraRepository<Bookings, String> {
}