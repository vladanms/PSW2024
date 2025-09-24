package org.ftn.PSW2024_backend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ScheduleDTO {
	
	private String name;
	
	private String description;
	
	private String category;
	
	private int difficulty;
	
	private int price;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime time;
	
    private String guideName;

	public ScheduleDTO(String name, String description, String category, int difficulty, int price, LocalDateTime time,
			String guideName) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.difficulty = difficulty;
		this.price = price;
		this.time = time;
		this.guideName = guideName;
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

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
    
    

}
