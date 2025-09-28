package org.ftn.PSW2024_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;

import org.ftn.PSW2024_backend.model.User;
import org.ftn.PSW2024_backend.service.UserService;
import org.ftn.PSW2024_backend.dto.LoginDTO;
import org.ftn.PSW2024_backend.dto.MaliciousDTO;
import org.ftn.PSW2024_backend.dto.RegisterDTO;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
		
		 Map<String, String> response = new HashMap<>();
		 try {
	            User user = userService.authenticate(loginDTO);
	            response.put("username", user.getUsername());
	            response.put("type", user.getType());
	            return ResponseEntity.ok(response);
	        } catch (BadCredentialsException e) {
	            response.put("error", e.getMessage());
	            return ResponseEntity.badRequest().body(response);
	        } catch (ForbiddenException e) {
	            response.put("error", e.getMessage());
	            return ResponseEntity.badRequest().body(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("error", "An error occured while processing the login request.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO userDTO) throws MessagingException, UnsupportedEncodingException
	{

		Map<String, String> response = new HashMap<>();
		
		try {
			String register = userService.register(userDTO);
		if(register.equals("usernameError"))
		{
			response.put("error", "Username taken. Please select another one.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		if(register.equals("emailError"))
		{
			response.put("error", "This e-mail is already registered.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		response.put("registered", userDTO.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing the registration request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	 @GetMapping("/getRewardPoints/{tourist}")
	 public ResponseEntity<Integer> getAvailable(@PathVariable String tourist) {
	     try {
	            return new ResponseEntity<>(userService.getRewardPoints(tourist), HttpStatus.OK); 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }
	 
	 @PostMapping("/setInterests/{tourist}/{interests}")
	 public ResponseEntity<Map<String, String>> setInterests(@PathVariable String tourist, @PathVariable String[] interests)
	 {
		 Map<String, String> response = new HashMap<>();
		 
			try {
				String res = userService.setInterests(tourist, interests);

			response.put("success", "Succesfully updated");
			return new ResponseEntity<>(response, HttpStatus.OK);
			 } catch (Exception e) {
			        e.printStackTrace();
			        response.put("error", "An error occurred while processing your request.");
			        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
	 }
	 
	 @GetMapping("/getMaliciousUsers")
	 public ResponseEntity<List<MaliciousDTO>> getMaliciousUsers()
	 {
		 try {
	          return new ResponseEntity<>(userService.getMaliciousUsers(), HttpStatus.OK); 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }
	 
	 @PostMapping("/banUser/{username}")
	 public ResponseEntity<Map<String, String>> banUser(@PathVariable String username)
	 {
		 Map<String, String> response = new HashMap<>();
		 
			try {
				Boolean res = userService.banUser(username);
				if (res) 
				{
					response.put("success", "User banned");
				}
				if(!res) 
				{
					response.put("success", "User unbanned");
				}
			return new ResponseEntity<>(response, HttpStatus.OK);
			 } catch (Exception e) {
			        e.printStackTrace();
			        response.put("error", "An error occurred while processing your request.");
			        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
	 }
}
