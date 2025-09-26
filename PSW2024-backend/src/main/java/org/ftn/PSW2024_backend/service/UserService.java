package org.ftn.PSW2024_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.ftn.PSW2024_backend.model.*;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.ftn.PSW2024_backend.dto.RegisterDTO;
import org.ftn.PSW2024_backend.dto.LoginDTO;

@Service
public class UserService{

	@Autowired
	private UserRepository users;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public String getUserType(String username)
	{
		return(users.findByUsername(username).getType());
	}
	
	public User authenticate(LoginDTO loginDTO) {
		 try {
			  Authentication auth = authenticationManager.authenticate(
			            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
			        );
			  		SecurityContextHolder.getContext().setAuthentication(auth);
			        return users.findByUsername(loginDTO.getUsername());
			 } 
			 catch (AuthenticationException e) {
				 System.out.println("LOGIN USER" + users.findByUsername(loginDTO.getUsername()) + " PASSWORD " + users.findByUsername(loginDTO.getPassword()));
				 throw new BadCredentialsException("Invalid username or password");
			    } 
	}
	
	public String register(RegisterDTO regDTO) throws MessagingException, UnsupportedEncodingException {
		User toRegister = users.findByEmail(regDTO.getEmail());
		if(toRegister != null) {
			return "emailError";
		}
		toRegister = users.findByUsername(regDTO.getUsername());
		if(toRegister != null) {
			return "usernameError";
		}
		
		List<UserInterests> interests = new ArrayList<UserInterests>();
		for(String interest : regDTO.getInterests())
		{
			interests.add(UserInterests.valueOf(interest));
		}
		
	    Tourist newUser = new Tourist(regDTO.getUsername(), encoder.encode(regDTO.getPassword()), regDTO.getEmail(),
	    		regDTO.getName(), regDTO.getSurname(), interests);
		
		users.save(newUser);
		return "success";
	}
	
	public int getRewardPoints(String username)
	{
		Tourist tourist = (Tourist) users.findByUsername(username);
		return tourist.getRewardPoints();
	}

}
