package com.taxi.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.taxi.auth.UserPrincipal;
import com.taxi.entity.CarClass;
import com.taxi.entity.Ride;
import com.taxi.entity.Ride.RideBuilder;
import com.taxi.entity.dto.RideDTO.RideDTOBuilder;
import com.taxi.entity.User;
import com.taxi.entity.dto.RideDTO;
import com.taxi.exception.UserInfoNotFoundException;
import com.taxi.service.UserInfoService;

@Controller
public class RideDetailsController {
	
	@Autowired
	UserInfoService userInfoService;
	

	@PostMapping("/rideDetails")
	public String rideDetailsPage(@RequestParam("posFrom") String posFrom, @RequestParam("posTo") String posTo, 
			@RequestParam("numOfPass") String numOfPassFromRequest, @RequestParam("carClass") String carClass,
			Authentication auth, HttpServletRequest req, Model m) throws IOException {
		
		HttpSession session = req.getSession(false);
		
		User user = ((UserPrincipal) auth.getPrincipal()).getUser();
		
		GeoApiContext sc = new GeoApiContext.Builder().apiKey("Secret Key").build();
		DistanceMatrix matrix = null;
		try {
			matrix = DistanceMatrixApi.newRequest(sc).origins(posFrom.replace(' ', '+'))
					.destinations(posTo.replace(' ', '+')).mode(TravelMode.DRIVING).await();
		} catch (ApiException | InterruptedException | IOException e1) {
			e1.printStackTrace();
		} finally {
			sc.close();
		}

		String dist = matrix.rows[0].elements[0].distance.toString();
		

		int discount;
		try {
			discount = (userInfoService.getTotalSpent((int) session.getAttribute("user_id")) / 100);
		} catch (UserInfoNotFoundException e) {
			return "WEB-INF/jsp/error.jsp";
		}

		if (Integer.parseInt(numOfPassFromRequest) <= CarClass.valueOf(carClass).getNumOfSeats()) {

			RideDTO r = new RideDTOBuilder().setPosFrom(posFrom).setPosTo(posTo)
					.setPrice(Math.round(Float.parseFloat(dist.substring(0, dist.indexOf(" ")))
							* (CarClass.valueOf(carClass).getPricePerKm())) - discount)
					.setUser(user).build();

			
			m.addAttribute("distance", dist);
			m.addAttribute("price", r.getPrice());
			m.addAttribute("time", matrix.rows[0].elements[0].duration);
			m.addAttribute("disc", discount);

			session.setAttribute("carClass", carClass);
			session.setAttribute("ride", r);

		} else {
			double numOfPass = Integer.parseInt(numOfPassFromRequest);
			double opt1Seat = CarClass.valueOf(carClass).getNumOfSeats();
			int opt1numOfCars = (int) Math.ceil(numOfPass / opt1Seat);
			double newDist = Double.parseDouble(dist.substring(0, dist.indexOf(" ")));

			CarClass newClass = carClass.equals("ECONOM") ? CarClass.valueOf("ECONOMXL")
					: CarClass.valueOf("VAN");

			int seat2 = newClass.getNumOfSeats();
			int opt2numOfCars = (int) Math.ceil(numOfPass / seat2);
			int opt1price = (int) (((CarClass.valueOf(carClass).getPricePerKm() * newDist)
					- discount) * opt1numOfCars);
			int opt2price = (int) (((newClass.getPricePerKm() * newDist) - discount) * opt2numOfCars);

			HashMap<String, String> opt1 = new HashMap<>();
			opt1.put("carClass", carClass);
			opt1.put("price", String.valueOf(opt1price));
			opt1.put("numOfCars", String.valueOf(opt1numOfCars));

			HashMap<String, String> opt2 = new HashMap<>();
			opt2.put("carClass", newClass.toString());
			opt2.put("price", String.valueOf(opt2price));
			opt2.put("numOfCars", String.valueOf(opt2numOfCars));

			session.setAttribute("option1", opt1);
			session.setAttribute("option2", opt2);
			session.setAttribute("dist", dist);
			session.setAttribute("time", matrix.rows[0].elements[0].duration);
			session.setAttribute("disc", discount);

			RideDTO r = new RideDTOBuilder().setPosFrom(posFrom).setPosTo(posTo)
					.setUser(user).build();

			session.setAttribute("ride", r);

		}
		
		return "WEB-INF/jsp/client/rideDetails.jsp";
	}
}
