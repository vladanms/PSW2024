package org.ftn.PSW2024_backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulerConfig {
	 @Bean
	 public ThreadPoolTaskScheduler taskScheduler() {
	        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	        scheduler.setPoolSize(10);
	        scheduler.initialize();
	        return scheduler;
	    }
}
