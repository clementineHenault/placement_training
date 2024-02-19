package com.placement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class Representative {
	private String username;
	private String password;
	
	public Representative() {
		
	}
	
	public Representative(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Representative [username=" + username + ", password=" + password + "]";
	}
}
