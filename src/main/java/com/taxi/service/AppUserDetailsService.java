package com.taxi.service;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taxi.auth.UserPrincipal;
import com.taxi.entity.User;
import com.taxi.repo.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ServletContext servletContext;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User us = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s has not been found!", username)));
		
		log.info("found user in db: "+us);
		return new UserPrincipal(us);
	}
}