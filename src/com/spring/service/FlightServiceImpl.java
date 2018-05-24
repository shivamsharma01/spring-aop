package com.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Flight;
import com.spring.repository.FlightRepository;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
	private FlightRepository flightRepo;

	@Override
	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		this.flightRepo.addFlight(flight);
	}

	@Override
	public List<Flight> searchFlight(String source, String destination, Date journeyDate) {
		return this.flightRepo.searchFlight(source, destination, journeyDate);
	}

	public FlightRepository getFlightRepo() {
		return flightRepo;
	}
	
	@Autowired
	public void setFlightRepo(FlightRepository flightRepo) {
		this.flightRepo = flightRepo;
	}
	
}
