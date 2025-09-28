package org.ftn.PSW2024_backend.dto;

public class FileComplaintDTO {

    private String tourist; 
    private String guide;
    private String tourId; 
    private String name; 
    private String description; 
    
    public FileComplaintDTO() {}

    
    
	public FileComplaintDTO(String tourist, String guide, String tourId, String name, String description) {
		this.tourist = tourist;
		this.guide = guide;
		this.tourId = tourId;
		this.name = name;
		this.description = description;
	}

	public String getTourist() {
		return tourist;
	}

	public void setTourist(String tourist) {
		this.tourist = tourist;
	}
	
	

	public String getGuide() {
		return guide;
	}



	public void setGuide(String guide) {
		this.guide = guide;
	}



	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
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

	
    
    
	
}
