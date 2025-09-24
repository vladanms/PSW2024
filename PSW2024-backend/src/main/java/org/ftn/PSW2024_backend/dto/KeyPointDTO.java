package org.ftn.PSW2024_backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class KeyPointDTO {
	
	private String tourId;
	private String name;
	private String description;
	private MultipartFile image;
	private String longitude;
	private String latitude;
    
    
    
	public KeyPointDTO(String tourId, String name, String description, MultipartFile image, String longitude, String latitude) {
		this.tourId = tourId;
		this.name = name;
		this.description = description;
		this.image = image;
		this.longitude = longitude;
		this.latitude = longitude;
	}



	public String getTourId() {
		return tourId;
	}



	public void setTourId(String postId) {
		this.tourId = postId;
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



	public MultipartFile getImage() {
		return image;
	}



	public void setImage(MultipartFile image) {
		this.image = image;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	

}
