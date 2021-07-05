package com.taxi.controllers;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxi.entity.Car;
import com.taxi.entity.CarClass;
import com.taxi.entity.Ride;
import com.taxi.entity.dto.RideDTO;
import com.taxi.exception.CarNotFoundException;
import com.taxi.repo.CarRepository;
import com.taxi.entity.Ride.RideBuilder;
import com.taxi.entity.User;
import com.taxi.service.RideService;

@Controller
public class RideConfirmed {

	@Autowired
	RideService rideService;
	@Autowired
	CarRepository carRepo;

	private void addRidesForOption(String option, List<Car> resCars, RideDTO r, HttpSession session)
			throws CarNotFoundException {
		
		HashMap<String, String> optionMap = (HashMap<String, String>) session.getAttribute(option);
		
		for (int i = 0; i < Integer.parseInt(optionMap.get("numOfCars")); i++) {
			Ride ride = new Ride(r);
			ride.setPrice(Math.round(Float
					.parseFloat(((String) session.getAttribute("dist")).substring(0,
							((String) session.getAttribute("dist")).indexOf(" ")))
					* (CarClass.valueOf((String) optionMap.get("carClass")).getPricePerKm())
					- (int) session.getAttribute("disc")));
			
			resCars.add(rideService.addRide(ride, optionMap.get("carClass")));
		}
	}

	@RequestMapping("/rideConfirmed")
	public String rideConfirmedPage(@RequestParam("opt") int opt, HttpServletRequest req) {
		
		HttpSession session = req.getSession();

		RideDTO r = (RideDTO) session.getAttribute("ride");
		if(r == null) {
			return "index.jsp";
		}
		
		
		List<Car> resCars = new ArrayList<Car>();
		try {
			switch (opt) {
			case 1:
				System.out.println("1");
				addRidesForOption("option1", resCars, r, session);
				break;
			case 2:
				System.out.println("2");
				addRidesForOption("option2", resCars, r, session);
				break;
			default:
				System.out.println("default");
				resCars.add(rideService.addRide(new Ride(r), String.valueOf(session.getAttribute("carClass"))));
				break;
			}
		} catch (CarNotFoundException e) {
			e.printStackTrace();
		}

		req.setAttribute("cars", resCars);

		session.removeAttribute("option1");
		session.removeAttribute("option2");
		session.removeAttribute("dist");
		session.removeAttribute("time");
		session.removeAttribute("ride");
		session.removeAttribute("carClass");

		return "WEB-INF/jsp/client/rideConfirmed.jsp";
	}
}