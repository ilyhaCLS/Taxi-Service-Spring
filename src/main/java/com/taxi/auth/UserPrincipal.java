package com.taxi.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.taxi.entity.User;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private static User user;
	
	public UserPrincipal(User user) {
		UserPrincipal.user = user;
	}
	
	public User getUser() {
		return UserPrincipal.user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//equals & hashcode methods are used for disabling concurrent sessions
	@Override
	public boolean equals(Object otherUser) {
	    if(otherUser == null) return false;
	    else if (!(otherUser instanceof UserDetails)) return false;
	    else return (otherUser.hashCode() == hashCode());
	}

	@Override
	public int hashCode() {
	    return user.getUsername().hashCode() ;
	}
}