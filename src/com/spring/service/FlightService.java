package com.spring.service;

import java.util.Date;
import java.util.List;

import com.spring.domain.Flight;

public interface FlightService {
	public void addFlight(Flight flight);

	public List<Flight> searchFlight(String source, String destination, Date journeyDate);
}
