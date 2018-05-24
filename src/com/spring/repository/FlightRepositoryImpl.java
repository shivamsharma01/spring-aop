package com.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.domain.Flight;

@Repository("flightRepository")
public class FlightRepositoryImpl implements FlightRepository {

	private static int flightId = 1001;
	private static List<Flight> flights;

	static {
		flights = new ArrayList<>();
		String[] airlines = new String[] { "Indigo", "SpiceJet", "Vistara", "GoAir", "Air India" };
		String[] location = new String[] { "Delhi", "Pune", "Bangalore", "Mumbai", "Chandigarh" };
		Double[] fare = new Double[] { 4000.d, 3800.d, 3200.d, 5000.d, 4500.0d };
		Integer[] seatCount = new Integer[] { 40, 32, 56, 80, 60 };
		Flight flight;
		for (int i = 1; i < 20; i++) {
			flight = new Flight();
			flight.setFlightId(flightId++);
			flight.setAirlines(airlines[(int) Math.floor(Math.random() * airlines.length)]);
			int sourceLocation = (int) Math.floor(Math.random() * location.length);
			int destinationLocation = (int) Math.floor(Math.random() * location.length);
			while (sourceLocation == destinationLocation) {
				destinationLocation = (int) Math.floor(Math.random() * location.length);
			}
			flight.setSource(location[sourceLocation]);
			flight.setDestination(location[destinationLocation]);
			flight.setFare(fare[(int) Math.floor(Math.random() * fare.length)]);
			Date date = new Date();
			int dateOfmonth = (int) Math.floor(Math.random() * 31);
			int month = (int) Math.floor(Math.random() * 12);
			int year = (int) Math.floor(Math.random() * 19)+2000;
			date.setDate(dateOfmonth);
			date.setMonth(month);
			date.setYear(year);
			flight.setJourneyDate(date);
			flight.setSeatCount(seatCount[(int) Math.floor(Math.random() * seatCount.length)]);
			flights.add(flight);
		}
	}

	@Override
	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		flight.setFlightId(flightId++);
		flights.add(flight);
	}

	@Override
	public List<Flight> searchFlight(String source, String destination, Date journeyDate) {
		// TODO Auto-generated method stub
		List<Flight> filteredList = new ArrayList<>();
		Flight matchingFlight = null;
		for (Flight flight : flights) {
			if (flight.getSource().equals(source) && flight.getDestination().equals(destination)
					&& flight.getJourneyDate().compareTo(journeyDate) == 0) {
				matchingFlight = new Flight();
				matchingFlight.setFlightId(flight.getFlightId());
				matchingFlight.setAirlines(flight.getAirlines());
				Double fare = flight.getFare();
				if (journeyDate.getMonth() == 0 || journeyDate.getMonth() == 11) {
					fare *= 1.2;
				}
				matchingFlight.setFare(fare);
				matchingFlight.setSeatCount(flight.getSeatCount());
				filteredList.add(matchingFlight);
			}
		}
		return filteredList;
	}

}
