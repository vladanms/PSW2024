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


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.ftn.PSW2024_backend.model.*;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.ftn.PSW2024_backend.dto.RegisterDTO;
import org.ftn.PSW2024_backend.dto.LoginDTO;

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository users;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUsername(username);
        
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword());

        
        if (user.getType().equals(UserType.RegisteredUser)) {
            builder.roles("REGISTERED_USER");
        } else if (user.getType().equals(UserType.Guide)) {
            builder.roles("GUIDE");
        } else if (user.getType().equals(UserType.Admin)) {
            builder.roles("ADMIN");
        } else {
            builder.roles("");
        }

        return builder.build();

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
				 throw new BadCredentialsException("Invalid username or password");
			    } 
	}
	
	public String register(RegisterDTO regDTO, PasswordEncoder encoder) throws MessagingException, UnsupportedEncodingException {
		User toRegister = users.findByEmail(regDTO.getEmail());
		if(toRegister != null) {
			return "emailError";
		}
		toRegister = users.findByUsername(regDTO.getUsername());
		if(toRegister != null) {
			return "usernameError";
		}
		
	    RegisteredUser newUser = new RegisteredUser(regDTO.getUsername(), regDTO.getPassword(), regDTO.getEmail(),
	    		regDTO.getName(), regDTO.getSurname(), regDTO.getInterests());
		
		users.save(newUser);
		return "success";
	}
	

}
