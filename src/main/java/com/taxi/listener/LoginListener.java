package com.taxi.listener;

import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.taxi.auth.UserPrincipal;
import com.taxi.exception.UserInfoNotFoundException;
import com.taxi.service.UserInfoService;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	
	private static final Logger log = LoggerFactory.getLogger(LoginListener.class);
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	UserInfoService userInfoService;
	
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		
		UserPrincipal userPrincipal = (UserPrincipal) event.getAuthentication().getPrincipal();
		int userId = userPrincipal.getUser().getId();
		
		httpSession.setAttribute("user_id", userId);
		
		try {
			httpSession.setAttribute("name", userInfoService.getUserFirstname(userId));
		} catch (UserInfoNotFoundException e) {
			e.printStackTrace();
		}
		
		int hour = LocalTime.now().getHour();
		String greeting = null;
		if(hour < 6) {
			greeting = "header.night";
		}else if(hour > 5 && hour < 12) {
			greeting = "header.morning";
		}else if(hour > 11 & hour < 18) {
			greeting = "header.day";
		}else {
			greeting = "header.evening";
		}
		httpSession.setAttribute("greeting", greeting);
		
		log.info("successfull login for id: " + userId);
	}
}