package com.taxi.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class LoginValidationFilter extends GenericFilterBean {
	private static final String LOGIN_REGEX = "^[a-zA-Z0-9_].{1,15}$";
	private static final String PASSWORD_REGEX = "^[a-zA-Z0-9_!@#&()–[{}]:;',?/*~$^+=<>].{1,15}$";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (((HttpServletRequest) req).getMethod().equals("POST")) {

			HttpServletResponse httpRes = (HttpServletResponse) res;

			if (!Pattern.matches(LOGIN_REGEX, req.getParameter("username"))
					|| !Pattern.matches(PASSWORD_REGEX, req.getParameter("password"))) {

				httpRes.sendRedirect("/login?form");
				return;
			}
		}
		chain.doFilter(req, res);
	}
}