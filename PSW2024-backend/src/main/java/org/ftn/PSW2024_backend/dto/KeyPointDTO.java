package org.ftn.PSW2024_backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class KeyPointDTO {
	
	private Long tourId;
	private String name;
	private String description;
	private MultipartFile image;
	private float longitude;
	private float latitude;
    
    
    
	public KeyPointDTO(Long tourId, String name, String description, MultipartFile image, float longitude, float latitude) {
		this.tourId = tourId;
		this.name = name;
		this.description = description;
		this.image = image;
		this.longitude = longitude;
		this.latitude = latitude;
	}



	public Long getTourId() {
		return tourId;
	}



	public void setTourId(Long postId) {
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
