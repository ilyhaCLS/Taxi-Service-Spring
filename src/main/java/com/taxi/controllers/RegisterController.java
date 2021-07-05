package com.taxi.controllers;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxi.entity.Role;
import com.taxi.entity.User;
import com.taxi.entity.User.UserBuilder;
import com.taxi.entity.UserInfo;
import com.taxi.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String regUser(@RequestParam("login") String login, @RequestParam("password") String password,
			@RequestParam("first") String first, @RequestParam("last") String last, HttpServletRequest request,
			HttpServletResponse res) {

		if (!isValid(login, password, first, last)) {
			res.setHeader("message_info", "wrong_reg_form");
			return "WEB-INF/register.jsp";
		}

		UserInfo userInfo = new UserInfo(first, last);

		User user = new UserBuilder().setLogin(login).setPassword(passwordEncoder.encode(password))
				.setRole(Role.ROLE_CLIENT.toString()).setUserInfo(userInfo).build();

		userInfo.setUser(user);

		if (!userService.regUser(user)) {
			res.setHeader("message_info", "already_registered");
			return "WEB-INF/register.jsp";
		}

		return "/";
	}

	private boolean isValid(String login, String password, String first, String last) {
		final String LOGIN_REGEX = "^[a-zA-Z0-9_].{1,15}$";
		final String NAME_REGEX = "^[a-zA-Zа-яА-ЯёЁІіЇїЄє].{1,31}$";
		final String PASSWORD_REGEX = "^[a-zA-Z0-9_!@#&()–[{}]:;',?/*~$^+=<>].{1,15}$";

		if (!Pattern.matches(LOGIN_REGEX, login) || !Pattern.matches(NAME_REGEX, first)
				|| !Pattern.matches(NAME_REGEX, last) || !Pattern.matches(PASSWORD_REGEX, password)) return false;
		
		return true;
	}
}