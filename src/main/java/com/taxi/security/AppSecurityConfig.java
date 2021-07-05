package com.taxi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.taxi.filter.LanguageSetFilter;
import com.taxi.filter.LoginValidationFilter;
import com.taxi.service.AppUserDetailsService;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(appUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public FilterRegistrationBean<LanguageSetFilter> languageSetFilter() {
	    FilterRegistrationBean<LanguageSetFilter> registrationBean = new FilterRegistrationBean<>();
	    registrationBean.setFilter(new LanguageSetFilter());
	    registrationBean.addUrlPatterns("/");
	    registrationBean.addUrlPatterns("/login");
	    registrationBean.addUrlPatterns("/register");
	    return registrationBean;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(new LoginValidationFilter() , UsernamePasswordAuthenticationFilter.class)
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/account").hasRole("CLIENT")
			.antMatchers(HttpMethod.GET, "/ride").hasRole("CLIENT")
			.antMatchers(HttpMethod.POST, "/rideDetails").hasRole("CLIENT")
			.antMatchers(HttpMethod.POST, "/rideConfirmed").hasRole("CLIENT")
			.antMatchers(HttpMethod.GET, "/adminPage").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/showRides").hasRole("ADMIN")
			.anyRequest()
			.permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/")
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.maximumSessions(1)
	            .maxSessionsPreventsLogin(true);
	}
}