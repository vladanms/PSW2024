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
import javax.ws.rs.ForbiddenException;

import org.ftn.PSW2024_backend.model.*;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.ftn.PSW2024_backend.dto.RegisterDTO;
import org.ftn.PSW2024_backend.dto.LoginDTO;
import org.ftn.PSW2024_backend.dto.MaliciousDTO;

@Service
public class UserService{

	@Autowired
	private UserRepository users;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MessagingService messagingService;
	
	
	public String getUserType(String username)
	{
		return(users.findByUsername(username).getType());
	}
	
	public User authenticate(LoginDTO loginDTO) {	
		 try {
			 User user = users.findByUsername(loginDTO.getUsername());

			 if (user instanceof Guide && ((Guide) user).isBanned()) {
		            throw new ForbiddenException("Your account has been banned.");
		        }
			 if (user instanceof Tourist && ((Tourist) user).isBanned()) {
		            throw new ForbiddenException("Your account has been banned.");
		        }
			  Authentication auth = authenticationManager.authenticate(
			            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
			        );
			  		SecurityContextHolder.getContext().setAuthentication(auth);
			        return users.findByUsername(loginDTO.getUsername());

			 } 
			 catch (AuthenticationException e) {
				 System.out.println("LOGIN USER" + users.findByUsername(loginDTO.getUsername()) + " PASSWORD " + users.findByUsername(loginDTO.getUsername()).getPassword());
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
	
	public String setInterests(String username, String[] interests)
	{
		Tourist tourist = (Tourist) users.findByUsername(username);
		List<UserInterests> userInterests = new ArrayList<UserInterests>();
		
		for(String s : interests)
		{
			userInterests.add(UserInterests.valueOf(s));
		}
		
		tourist.setInterests(userInterests);
		users.save(tourist);
		return "success";
	}
	
	public List<MaliciousDTO> getMaliciousUsers()
	{		List<MaliciousDTO> maliciousList = new ArrayList<MaliciousDTO>();
		
		for(User user : users.findAllMaliciousGuides())
		{
			Guide guide = (Guide) user;
			maliciousList.add(new MaliciousDTO(user.getUsername(), user.getType(), guide.getPenaltyPoints(), guide.isBanned()));

		}
		
		for(User user : users.findAllMaliciousTourists())
		{

			Tourist tourist = (Tourist) user;
			maliciousList.add(new MaliciousDTO(user.getUsername(), user.getType(), tourist.getPenaltyPoints(), tourist.isBanned()));
		}		
			
			return maliciousList;
	}
	
	public boolean banUser(String username)
	{
		User user = users.findByUsername(username);
		
		if (user instanceof Guide) {
			if(!((Guide) user).isBanned())
			{
				try {
					messagingService.bannedMail(user);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			
			((Guide) user).setBanned(!((Guide) user).isBanned());
			users.save(((Guide) user));
			return ((Guide) user).isBanned();
		}
		if (user instanceof Tourist) {
			if(!((Tourist) user).isBanned())
			{
				try {
					messagingService.bannedMail(user);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			
			((Tourist) user).setBanned(!((Tourist) user).isBanned());
			users.save(((Tourist) user));
			return ((Tourist) user).isBanned();
		}
		throw new InternalError("Error");
		
	}

}
