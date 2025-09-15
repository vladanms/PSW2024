package org.ftn.PSW2024_backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("guide")
public class Guide extends User{
	
	@OneToMany(mappedBy = "guide",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tour> tours;
	
	@Column(unique = false, nullable = false)
	private int rewardPoints;
	
	@Column(unique = false, nullable = false)
	private int penaltyPoints;
	
	@Column(unique = false, nullable = false)
	private boolean isMalicious;
	
	@Column(unique = false, nullable = false)
	private boolean isBanned;
	
	@Column(unique = false, nullable = false)
	private boolean isAwarded;
	
	public Guide(Long id, String username, String password, String email, String name, String surname) {
		super(id, username, password, email, name, surname, UserType.RegisteredUser);
		this.tours = new ArrayList<>();
		this.rewardPoints = 0;
		this.penaltyPoints = 0;
		this.isMalicious = false;
		this.isBanned = false;
		this.isAwarded = false;
	}
	
	
	
}
