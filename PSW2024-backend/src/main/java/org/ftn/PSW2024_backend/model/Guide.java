package org.ftn.PSW2024_backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Guide")
public class Guide extends User{
	
	@OneToMany(mappedBy = "guide",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tour> tours;
	
	@Column(unique = false)
	private int rewardPoints;
	
	@Column(unique = false)
	private int penaltyPoints;
	
	@Column(unique = false)
	private boolean isMalicious;
	
	@Column(unique = false)
	private boolean isBanned;
	
	@Column(unique = false)
	private boolean isAwarded;

	public Guide() {
		super();
	}
	
	public Guide(String username, String password, String email, String name, String surname) {
		super(username, password, email, name, surname, "Guide");
		this.tours = new ArrayList<>();
		this.rewardPoints = 0;
		this.penaltyPoints = 0;
		this.isMalicious = false;
		this.isBanned = false;
		this.isAwarded = false;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

	public boolean isMalicious() {
		return isMalicious;
	}

	public void setMalicious(boolean isMalicious) {
		this.isMalicious = isMalicious;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public boolean isAwarded() {
		return isAwarded;
	}

	public void setAwarded(boolean isAwarded) {
		this.isAwarded = isAwarded;
	}
	
	
	
	
	
}
