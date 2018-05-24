package com.spring.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.config.AppConfig;
import com.spring.domain.Flight;
import com.spring.service.FlightService;

public class FlightClient {
	private static FlightService flightService;

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		flightService = context.getBean("flightService", FlightService.class);
		start();
		context.close();
	}

	private static void start() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome to BookMyFlight\nSelect\n1. To Add A Flight\n2. To View Flights\n3. To Exit");
			byte selection = scan.nextByte();
			menu(selection);
		}
	}

	private static void menu(byte selection) {
		switch (selection) {
		case 1:
			addFlight();
			break;
		case 2:
			searchFlight();
			break;
		case 3:
			System.out.println("Thanks For Visiting BookMyFlight");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Selection");
			break;
		}
	}

	private static void addFlight() {
		Flight flight = new Flight();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter The Airline");
		flight.setAirlines(scan.next());
		System.out.println("Enter Source");
		flight.setSource(scan.next());
		System.out.println("Enter Destination");
		flight.setDestination(scan.next());
		System.out.println("Enter Fare");
		flight.setFare(scan.nextDouble());
		System.out.println("Enter Seating Capacity");
		flight.setSeatCount(scan.nextInt());
		System.out.println("Enter Date Of Journey in MM/DD/YYYY format");
		flight.setJourneyDate(stringToDate(scan.next()));
		flightService.addFlight(flight);
	}

	private static void searchFlight() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Source");
		String source = scan.next();
		System.out.println("Enter Destination");
		String destination = scan.next();
		System.out.println("Enter Date of Journey in MM/DD/YYYY format");
		Date journeyDate = stringToDate(scan.next());
		List<Flight> filteredFlights = flightService.searchFlight(source, destination, journeyDate);
		for(Flight flight: filteredFlights) {
			System.out.print("FlightID : "+flight.getFlightId());
			System.out.print(", Airlines : "+flight.getAirlines());
			System.out.print(", Fare : "+flight.getFare());
			System.out.print(", Seats : "+flight.getSeatCount());
			System.out.println();
		}
	}

	private static Date stringToDate(String string) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
