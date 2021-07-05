package com.taxi.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangeLanguageController {

	@GetMapping("/changeLang")
	public void changeLanguage(@RequestParam String lang, HttpServletRequest req, HttpServletResponse res) {
		
		if(lang.equals("ru") || lang.equals("en")) {
			res.addCookie(new Cookie("lang", lang));
		}
		
		res.setHeader("Location", "/");
	    res.setStatus(302);
	}
}