package com.taxi.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.taxi.entity.User;
import com.taxi.repo.UserRepository;

@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	UserRepository userRepo;
	
	public boolean regUser(User user) {
		boolean res = false;
		try {
			userRepo.save(user);
		}catch(DataIntegrityViolationException e) {
			log.info("SQL Error Type : " + e.getClass().getName());
		    log.info("Error Message  : " + e.getMessage());
			return res;
		}
		
		res = true;
		return res;
	}
}