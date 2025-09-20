package org.ftn.PSW2024_backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

@Entity
@DiscriminatorValue("registeredUser")
public class Tourist extends User{

	@Column(unique = false, nullable = false)
	private int rewardPoints;
	
	@Column(unique = false, nullable = false)
	private int penaltyPoints;
	
	@Column(unique = false, nullable = false)
	private boolean isMalicious;
	
	@Column(unique = false, nullable = false)
	private boolean isBanned;
	
    @ManyToMany(targetEntity = Tour.class, mappedBy = "tourists")
    private List<Tour> tours;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "interests", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "interests")
    private List<UserInterests> interests;

	public Tourist(String username, String password, String email, String name, String surname, List<UserInterests> interests) {
		super(username, password, email, name, surname, UserType.Tourist);
		this.rewardPoints = 0;
		this.penaltyPoints = 0;
		this.isMalicious = false;
		this.isBanned = false;
		this.tours = new ArrayList<>(); 
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

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public List<UserInterests> getInterests() {
		return interests;
	}

	public void setInterests(List<UserInterests> interests) {
		this.interests = interests;
	}
	
	

}
