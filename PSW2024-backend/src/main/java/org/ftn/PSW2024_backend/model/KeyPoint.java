package org.ftn.PSW2024_backend.model;

import javax.persistence.Embeddable;

@Embeddable
public class KeyPoint {
	
	private String name;
	private String description;
	private String imagePath;
	private float longitude;
	private float latitude;
    
    
    
	public KeyPoint(String name, String description, String imagePath, float longitude, float latitude) {
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
    
    
	

}
