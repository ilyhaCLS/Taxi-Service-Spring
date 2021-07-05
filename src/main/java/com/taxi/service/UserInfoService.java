package com.taxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxi.entity.UserInfo;
import com.taxi.exception.UserInfoNotFoundException;
import com.taxi.repo.UserInfoRepository;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoRepository userInfoRepo;
	
	public UserInfo getUserInfo(int id) throws UserInfoNotFoundException {
		return userInfoRepo.findById(id).orElseThrow(UserInfoNotFoundException::new);
	}
	
	public String getUserFirstname(int id) throws UserInfoNotFoundException {
		return userInfoRepo.findFirstname(id).orElseThrow(UserInfoNotFoundException::new);
	}
	
	public int getTotalSpent(int id) throws UserInfoNotFoundException{
		return userInfoRepo.findTotalSpent(id);
	}
}