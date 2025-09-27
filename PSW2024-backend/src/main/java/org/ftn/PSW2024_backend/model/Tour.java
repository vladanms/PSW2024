package org.ftn.PSW2024_backend.model;

import java.time.LocalDateTime;
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int difficulty;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    @ManyToMany
    @JoinTable( name = "tourists",joinColumns = @JoinColumn(name = "tour_id"),inverseJoinColumns = @JoinColumn(name = "tourist_id") )
    private List<Tourist> tourists = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "keyPoints", joinColumns = @JoinColumn(name = "tour_id"))
    private List<KeyPoint> keyPoints = new ArrayList<>();

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();

    @Column(nullable = false)
    private boolean isPublished;

    @ElementCollection
    @CollectionTable(name = "grades", joinColumns = @JoinColumn(name = "tour_id"))
    private List<Grade> grades = new ArrayList<>();

    public Tour() {}
    
	public Tour(String name, String description, String category, int difficulty, int price, Guide guide, LocalDateTime time) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.difficulty = difficulty;
		this.price = price;
		this.guide = guide;
		this.time = time;
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

	public List<Tourist> getTourists() {
		return tourists;
	}

	public void setTourists(List<Tourist> tourists) {
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
	
    
    
    
}
