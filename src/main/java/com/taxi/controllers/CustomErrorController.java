package com.taxi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);

	@RequestMapping("/error")
	public String errorPage(HttpServletRequest req, HttpServletResponse res) {
		log.info("Error occured for ip: "+req.getRemoteAddr());
		
		return "WEB-INF/jsp/error_page.jsp";
	}
}