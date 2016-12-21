package com.sharky.spring.web.DAO;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.sharky.spring.web.validation.ValidEmail;

public class User {

	@NotBlank(message="Username cannot be blank")
	@Size(min=6,max=15, message="Username must be between 8 and 15 characters long")
	@Pattern(regexp="^\\w{8,}$",message="Username can only conisist of numbers,letters and the underscore character")
	private String username;
	
	@NotBlank(message="Password must not be black")
	@Size(min=6,max=15,message="Password must be between 6 and 15 characters long")
	@Pattern(regexp="^\\S+$", message="Password must not contain empty spaces")
	private String password;
	
	@ValidEmail(message="This does not appear to be a valid email")
	private String email;
	private boolean enabled;
	private String authority;

	public User() {

	}

	public User(String username, String password, String email, boolean enabled, String authority) {

		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
