package org.ftn.PSW2024_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.ftn.PSW2024_backend.service.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig{
	
	@Autowired
	@Lazy
	private CustomUserDetailsService userDetailsService;
	
    @Bean
	  public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedOrigins("http://localhost:4200")
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                        .allowCredentials(true);
	            }
	        };
	    }
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors(cors -> cors.configurationSource(request -> {
        		CorsConfiguration corsConfig = new CorsConfiguration();
        		corsConfig.addAllowedOrigin("http://localhost:4200");  
        		corsConfig.addAllowedMethod("*"); 
        		corsConfig.addAllowedHeader("*");
        		corsConfig.setAllowCredentials(true);  
        		return corsConfig;
        		}))
        .csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(authz -> authz
                                .antMatchers("/user/login", "/user/register").permitAll()
                                .antMatchers("/user/getRewardPoints/**", "/user/setInterests/**").hasRole("TOURIST")
                                .antMatchers("/user/getMaliciousUsers", "/user/banUser/**").hasRole("ADMIN")
                                .antMatchers("/tour/createTour", "/tour/publishTour/**", "/tour/addKeypoint",
                                		"/tour/cancelTour/**", "/tour/getDrafts/**", "/tour/getAllByGuide/**").hasRole("GUIDE")
                                .antMatchers("/tour/createTour", "/tour/publishTour/**", "/tour/addKeypoint",
                                		"/tour/getAvailable/**", "/tour/purchaseTours/**", "/tour/getPurchased/**", "/tour/gradeTour" ).hasRole("TOURIST")
                                .antMatchers("/complaint/createComplaint", "/complaint/getByTourist/**").hasRole("TOURIST")
                                .antMatchers("/complaint/getByGuide/**").hasRole("GUIDE")
                                .antMatchers("/complaint/getByStatus/**").hasRole("ADMIN")
                                .antMatchers("/complaint/updateStatus/**").hasAnyRole("GUIDE", "ADMIN")
                )
                .formLogin(login -> login.disable())
                .logout(logout -> logout
                                .logoutUrl("/user/logout")
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    response.setStatus(HttpServletResponse.SC_OK);
                                })
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                )
                .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        			
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }
}