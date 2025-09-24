package org.ftn.PSW2024_backend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    private Tourist user; 

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, unique = false)
    private Tour tour; 
   
    @Column(name = "name", nullable = false, unique = false)
    private String name; 
   
    @Column(name = "description", nullable = false, unique = false)
    private String description; 
    
    
	@Enumerated(EnumType.STRING) 
    @Column(nullable = false, unique = false)
    private ComplaintStatus status;


	public Complaint(Long id, Tourist user, Tour tour, String name, String description, ComplaintStatus status) {
		super();
		this.id = id;
		this.user = user;
		this.tour = tour;
		this.name = name;
		this.description = description;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Tourist getUser() {
		return user;
	}


	public void setUser(Tourist user) {
		this.user = user;
	}


	public Tour getTour() {
		return tour;
	}


	public void setTour(Tour tour) {
		this.tour = tour;
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


	public ComplaintStatus getStatus() {
		return status;
	}


	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}
	
	
	
	
}
