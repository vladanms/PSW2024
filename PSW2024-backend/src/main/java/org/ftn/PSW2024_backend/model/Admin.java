package org.ftn.PSW2024_backend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

	public Admin() {
		super();
	
	}

	public Admin(String username, String password, String email, String name, String surname, String type) {
		super(username, password, email, name, surname, type);
	}

}
