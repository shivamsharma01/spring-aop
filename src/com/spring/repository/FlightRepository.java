package com.spring.repository;

import java.util.Date;
import java.util.List;

import com.spring.domain.Flight;

public interface FlightRepository {
	public void addFlight(Flight flight);

	public List<Flight> searchFlight(String source, String destination, Date journeyDate);
}
