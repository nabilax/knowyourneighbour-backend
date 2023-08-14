package com.assessment.payload;

import java.util.HashSet;
import java.util.Set;

public class SignUpDto {

    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    
    public SignUpDto() {
        roles = new HashSet<>();
    }
    
	public SignUpDto(String name, String username, String email, String password, Set<String> roles) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 public Set<String> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<String> roles) {
	        this.roles = roles;
	    }

		@Override
		public String toString() {
			return "SignUpDto [name=" + name + ", username=" + username + ", email=" + email + ", password=" + password
					+ ", roles=" + roles + "]";
		}
	    
	
	
}
