package org.ftn.PSW2024_backend.dto;

import javax.persistence.Embeddable;


public class GradeDTO {

	private String tourId;
	private String tourist;  
    private String content;   
    private int grade;
    
    public GradeDTO() {}
    
	public GradeDTO(String tourId, String tourist, String content, int grade) {
		this.tourId = tourId;
		this.tourist = tourist;
		this.content = content;
		this.grade = grade;
	}
	
	
	
	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
	}

	public String getTourist() {
		return tourist;
	}
	public void setTourist(String tourist) {
		this.tourist = tourist;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}        
    
    
}
