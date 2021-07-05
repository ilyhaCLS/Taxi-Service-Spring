package com.taxi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxi.auth.UserPrincipal;
import com.taxi.entity.UserInfo;
import com.taxi.exception.UserInfoNotFoundException;
import com.taxi.service.RideService;
import com.taxi.service.UserInfoService;

@Controller
public class AccountController {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	RideService rideService;

	@GetMapping("/account")
	public String accountPage(Model m, Authentication auth, @RequestParam int p, HttpServletRequest req) {
		
		UserPrincipal up = (UserPrincipal)auth.getPrincipal();
		
		try {
			UserInfo usInfo = userInfoService.getUserInfo(up.getUser().getId());
			
			m.addAttribute("numOfPages", Math.ceil(rideService.getNumOfRides(up.getUser().getId()) / 10.0));
			m.addAttribute("first", usInfo.getFirst());
			m.addAttribute("last", usInfo.getLast());
			m.addAttribute("totalSpent", usInfo.getTotalSpent());
			
			req.setAttribute("rides", rideService.getRides(up.getUser().getId(), p));
		}catch(UserInfoNotFoundException e) {
			return "WEB-INF/jsp/client/error_page.jsp";
		}
		
		return "WEB-INF/jsp/client/account1.jsp";
	}
}