package com.taxi.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxi.entity.Ride;
import com.taxi.service.RideService;

@Controller
public class AdminPageController {
	private static final Logger log = LoggerFactory.getLogger(AdminPageController.class);
	
	
	@Autowired
	RideService rideService;

	@GetMapping("/adminPage")
	public String adminPage(Model m, ServletRequest req) {
		
		List<Integer> dayInfo =  rideService.getDayInfo(LocalDateTime.now().minusDays(1), LocalDateTime.now());
		int sum = dayInfo.stream().collect(Collectors.summingInt(Integer::intValue));
		
		
		m.addAttribute("ridesInADay", dayInfo.size());
		m.addAttribute("sumInADay", sum);
		
		log.info(req.getRemoteHost()+" visited admin page");
		return "WEB-INF/jsp/admin/adminPage.jsp";
	}
	
	@GetMapping("/showRides")
	public String ordersPage(Model m, HttpServletRequest req, 
			@RequestParam(required = true) String q, @RequestParam(required = false) String order,
			@RequestParam(required = true) String p) {
		
		HttpSession session  = req.getSession();

		List<Ride> rides = null;

		if (q.equals("all")) {
			switch (order) {
			case "desc":
				rides = rideService.getSortedRides("creationTime", "DESC", Integer.parseInt(p));
				break;
			case "asc":
				rides = rideService.getSortedRides("creationTime", "ASC", Integer.parseInt(p));
				break;
			case "exp":
				rides = rideService.getSortedRides("price", "DESC", Integer.parseInt(p));
				break;
			case "cheap":
				rides = rideService.getSortedRides("price", "ASC", Integer.parseInt(p));
				break;
			default:
				return "/error";
			}
			req.setAttribute("numOfPages", (int)Math.ceil(rideService.getNumOfRides() / 10.0));
		}
		
		if(q.equals("date")){
			if(req.getParameter("from") != null) {
				session.setAttribute("from", req.getParameter("from"));
				session.setAttribute("until", req.getParameter("until"));
			}
			
			rides = rideService.getRidesByPeriod(LocalDateTime.parse((CharSequence) session.getAttribute("from")),
					LocalDateTime.parse((CharSequence) session.getAttribute("until")), Integer.parseInt(p));
			
			req.setAttribute("numOfPages", (int)Math.ceil(rideService.getNumOfRides(LocalDateTime.parse((CharSequence) session.getAttribute("from")),
					LocalDateTime.parse((CharSequence) session.getAttribute("until"))) /10.0));
		}

		if (q.equals("us")) {
			if(req.getParameter("rides_user_id") != null) {
				session.setAttribute("rides_user_id", Integer.parseInt(req.getParameter("rides_user_id")));
			}
			
			rides = rideService.getRides((Integer)session.getAttribute("rides_user_id"), Integer.parseInt(p));
			req.setAttribute("numOfPages", (int)Math.ceil(rideService.getNumOfRides((Integer)session.getAttribute("rides_user_id")) /10.0));
		}

		req.setAttribute("rides", rides);
		
		log.info(req.getRemoteHost()+" visited orders page");
		return "WEB-INF/jsp/admin/orders.jsp";
	}
}