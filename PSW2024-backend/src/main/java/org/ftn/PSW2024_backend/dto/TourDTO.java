package org.ftn.PSW2024_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.Grade;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tourist;

public class TourDTO {

	
	    private Long id;
		private String name;
		private String description;
		private String category;
		private int difficulty;
		private int price;
		private LocalDateTime time;
	    private String guide;
	    private List<String> tourists;
	    private List<KeyPoint> keyPoints;
	    private List<Complaint> complaints;
	    private boolean isPublished;
	    private List<Grade> grades;
	    
	    
	    
		public TourDTO(Long id, String name, String description, String category, int difficulty, int price,
				LocalDateTime time, String guide, List<String> tourists, List<KeyPoint> keyPoints,
				List<Complaint> complaints, boolean isPublished, List<Grade> grades) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.category = category;
			this.difficulty = difficulty;
			this.price = price;
			this.time = time;
			this.guide = guide;
			this.tourists = tourists;
			this.keyPoints = keyPoints;
			this.complaints = complaints;
			this.isPublished = isPublished;
			this.grades = grades;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public int getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(int difficulty) {
			this.difficulty = difficulty;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public LocalDateTime getTime() {
			return time;
		}
		public void setTime(LocalDateTime time) {
			this.time = time;
		}
		public String getGuide() {
			return guide;
		}
		public void setGuide(String guide) {
			this.guide = guide;
		}
		public List<String> getTourists() {
			return tourists;
		}
		public void setTourists(List<String> tourists) {
			this.tourists = tourists;
		}
		public List<KeyPoint> getKeyPoints() {
			return keyPoints;
		}
		public void setKeyPoints(List<KeyPoint> keyPoints) {
			this.keyPoints = keyPoints;
		}
		public List<Complaint> getComplaints() {
			return complaints;
		}
		public void setComplaints(List<Complaint> complaints) {
			this.complaints = complaints;
		}
		public boolean isPublished() {
			return isPublished;
		}
		public void setPublished(boolean isPublished) {
			this.isPublished = isPublished;
		}
		public List<Grade> getGrades() {
			return grades;
		}
		public void setGrades(List<Grade> grades) {
			this.grades = grades;
		} 
	    
	    
}
