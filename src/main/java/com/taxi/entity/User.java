package com.taxi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length = 64)
	private String username;
	
	@Column(nullable=false, length = 64)
	private String password;
	
	@Column(nullable=false, length = 32)
	private String role;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
	private UserInfo userInfo;

	public User() {
		
	}
	

	private User(int id, String username, String password, String role, UserInfo userInfo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.userInfo = userInfo;
	}

	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}



	public static class UserBuilder {
		private int id;
		private String login;
		private String password;
		private String role;
		private UserInfo userInfo;
		
		public UserBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder setLogin(String login) {
			this.login = login;
			return this;
		}
		
		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder setRole(String role) {
			this.role = role;
			return this;
		}
		
		public UserBuilder setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
			return this;
		}
		
		public User build() {
			return new User(id, login, password, role, userInfo);
		}
		
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		
		User other = (User) obj;
		if(!username.equals(other.getUsername())) {
			return false;
		}else if (!password.equals(other.getPassword())){
			return false;
		}else if(!role.equals(other.getRole())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
}