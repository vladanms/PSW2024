package org.ftn.PSW2024_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "org.ftn.PSW2024_backend.model")
public class Psw2024BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Psw2024BackendApplication.class, args);
	}

}
