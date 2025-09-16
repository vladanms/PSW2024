package org.ftn.PSW2024_backend.dto;
import java.util.List;

import org.ftn.PSW2024_backend.model.UserInterests;

public class RegisterDTO {

	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private List <UserInterests> interests;
	
	
	
	public RegisterDTO(String username, String password, String email, String name, String surname,
			List<UserInterests> interests) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.interests = interests;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<UserInterests> getInterests() {
		return interests;
	}

	public void setInterests(List<UserInterests> interests) {
		this.interests = interests;
	}
	
	
	
}
