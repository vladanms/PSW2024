package org.ftn.PSW2024_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaliciousDTO {

	private String username;
	private String type;
	private int penaltyPoints;
	
	@JsonProperty("isBanned")
	private boolean isBanned;
	
	public MaliciousDTO() {}

	public MaliciousDTO(String username, String type, int penaltyPoints, boolean isBanned) {
		super();
		this.username = username;
		this.type = type;
		this.penaltyPoints = penaltyPoints;
		this.isBanned = isBanned;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	
	
	
}
