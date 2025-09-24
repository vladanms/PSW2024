package org.ftn.PSW2024_backend.service;

import org.ftn.PSW2024_backend.model.User;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository users;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByUsername(username);
        
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword());

        
        if (user.getType().equals("Tourist")) {
            builder.roles("TOURIST");
        } else if (user.getType().equals("Guide")) {
            builder.roles("GUIDE");
        } else if (user.getType().equals("Admin")) {
            builder.roles("ADMIN");
        } else {
            builder.roles("");
        }

        return builder.build();

	}
}
