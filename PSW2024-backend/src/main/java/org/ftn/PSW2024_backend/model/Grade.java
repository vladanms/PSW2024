package org.ftn.PSW2024_backend.model;

import javax.persistence.Embeddable;

@Embeddable
public class Grade {

	private String tourist;  
    private String content;   
    private int grade;
	public Grade(String tourist, String content, int grade) {
		super();
		this.tourist = tourist;
		this.content = content;
		this.grade = grade;
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
