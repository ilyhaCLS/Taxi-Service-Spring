package com.taxi.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LanguageSetFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {

		Optional<Cookie> maybeCookie = req.getCookies() == null ? Optional.empty()
				: Arrays.stream(req.getCookies()).filter(e -> e.getName().equals("lang")).findFirst();
		if (maybeCookie.isEmpty()) {
			res.addCookie(new Cookie("lang", "ru"));
		}

		filterChain.doFilter(req, res);
	}
}