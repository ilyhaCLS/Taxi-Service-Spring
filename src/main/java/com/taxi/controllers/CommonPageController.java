package com.taxi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxi.entity.Role;

/**
 * Common Page Controller processes every request to general web-site pages.
 * 
 *
 */

@Controller
public class CommonPageController {

	@RequestMapping("/")
	public String indexPage(Authentication auth) {
		if (auth != null) {
			if (auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()))) {
				return "/adminPage";
			}
		}

		return "/index.jsp";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "WEB-INF/login.jsp";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "WEB-INF/register.jsp";
	}

	/**
	 * First, method removes all data about possible previous order, then returns
	 * page for new taxi order
	 */
	@GetMapping("/ride")
	public String ridePage(HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session != null) {
			session.removeAttribute("option1");
			session.removeAttribute("option2");
			session.removeAttribute("dist");
			session.removeAttribute("time");
			session.removeAttribute("ride");
			session.removeAttribute("carClass");
		}

		return "WEB-INF/jsp/client/ride.jsp";
	}
}