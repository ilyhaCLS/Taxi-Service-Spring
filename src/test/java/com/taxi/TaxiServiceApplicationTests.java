package com.taxi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.taxi.entity.Ride;
import com.taxi.entity.Role;
import com.taxi.entity.User;
import com.taxi.service.AppUserDetailsService;
import com.taxi.service.RideService;
import com.taxi.service.UserInfoService;
import com.taxi.service.UserService;
import com.taxi.entity.User.UserBuilder;
import com.taxi.entity.UserInfo;
import com.taxi.exception.UserInfoNotFoundException;
import com.taxi.repo.UserRepository;

@SpringBootTest
class TaxiServiceApplicationTests {

	@Autowired
	RideService rideService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AppUserDetailsService userDetailsService;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testRideService() {
		List<Ride> rides = rideService.getRidesByPeriod(LocalDateTime.now().minusDays(1), LocalDateTime.now(), 1);
		
		assertTrue(rideService.getRidesByPeriod(LocalDateTime.now(), LocalDateTime.now(), 1).isEmpty());
		assertEquals(0, rides.size());
		
		rides = rideService.getRides(2, 1);
		assertFalse(rides.isEmpty());
		
		assertEquals(0, rideService.getDayInfo(LocalDateTime.now().minusDays(1), LocalDateTime.now()).size());
		
		assertEquals(rideService.getNumOfRides(LocalDateTime.now().minusDays(30), LocalDateTime.now()), rideService.getNumOfRides());
		
		assertEquals(0, rideService.getNumOfRides(LocalDateTime.now().minusDays(1), LocalDateTime.now()));
		
		assertEquals(0, rideService.getNumOfRides(777));//777 is non existing user, thus should return 0
	}
	
	
	@Test
	public void testUserService() {
		UserInfo usInfo = new UserInfo("test_first","test_last");
		User us = new UserBuilder().setLogin("test").setPassword("test")
				.setRole(Role.ROLE_CLIENT.toString()).setUserInfo(usInfo).build();
		usInfo.setUser(us);
		
		assertTrue(userService.regUser(us));
	}
	
	@Test
	public void testUserInfoService() throws UserInfoNotFoundException {
		User us = userRepo.findByUsername("test").orElse(null);
		
		assertEquals(0, userInfoService.getTotalSpent(us.getId()));
		assertEquals("test_first", userInfoService.getUserFirstname(us.getId()));
		assertEquals("test_last", userInfoService.getUserInfo(us.getId()).getLast());
	}
	
	@Test
	public void testAppUserDetailsService() {
		Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername("test").getAuthorities();
		assertTrue(authorities.contains(new SimpleGrantedAuthority(Role.ROLE_CLIENT.toString())));
	}
}