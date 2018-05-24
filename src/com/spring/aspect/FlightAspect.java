package com.spring.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FlightAspect {
	
	@AfterReturning("execution(* com.spring.service.FlightService.addFlight(..))")
	public void afterAddingFlight(JoinPoint joinPoint) {
		System.out.println("signature :"+joinPoint.getSignature());
		System.out.println("Current Timestamp :"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
	}
}
