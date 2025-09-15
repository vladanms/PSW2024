package org.ftn.PSW2024_backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
    
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	
	@Column(name = "difficulty", unique = false, nullable = false)
	private int difficulty;
	
	@Column(name = "price", unique = false, nullable = false)
	private int price;
	
    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;
    
    @ManyToMany
    @JoinTable(name = "users", 
    		   joinColumns = @JoinColumn(name = "tour_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<RegisteredUser> tourists;
    
    @ElementCollection
    @CollectionTable(name = "keyPoints", joinColumns = @JoinColumn(name = "tour_id"))
    private List<KeyPoint> keyPoints;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;
    
    @Column(name = "published", unique = false, nullable = false)
    private boolean isPublished;
    
    @ElementCollection
    @CollectionTable(name = "grades", joinColumns = @JoinColumn(name = "tour_id"))
    private List<Grade> grades; 

	public Tour(Long id, String name, String description, int difficulty, int price, Guide guide) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.difficulty = difficulty;
		this.price = price;
		this.guide = guide;
		this.tourists = new ArrayList<>();
		this.keyPoints = new ArrayList<>();
		this.complaints = new ArrayList<>();
		this.isPublished = false;
		this.grades = new ArrayList<>();
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

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public List<RegisteredUser> getTourists() {
		return tourists;
	}

	public void setTourists(List<RegisteredUser> tourists) {
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
