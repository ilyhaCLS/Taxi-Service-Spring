package com.taxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class UserInfo {
	
	@Id
	private int userId;
	
	@Column(nullable=false, length = 64)
	private String first;
	
	@Column(nullable=false, length = 64)
	private String last;
	
	@Column(nullable=false)
	private int totalSpent;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "userId")
	private User user;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public int getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(int totalSpent) {
		this.totalSpent = totalSpent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", first=" + first + ", last=" + last + ", totalSpent=" + totalSpent
				+ "]";
	}
}