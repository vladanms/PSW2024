package org.ftn.PSW2024_backend.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ftn.PSW2024_backend.event.ComplaintStatusUpdatedEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    private Tourist tourist; 
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false, unique = false)
    private Guide guide; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, unique = false)
    private Tour tour; 
   
    @Column(name = "name", nullable = false, unique = false)
    private String name; 
   
    @Column(name = "description", nullable = false, unique = false)
    private String description; 
    
    @Transient
    private ComplaintStatus status;
    
    public ComplaintStatusUpdatedEvent updateStatus(ComplaintStatus status) {
        ComplaintStatus oldStatus = this.status;
        if(this.status == null)
        {
        	oldStatus = ComplaintStatus.OnHold;
        }
        this.status = status;
        return new ComplaintStatusUpdatedEvent(this.id, oldStatus, status);
    }
	
    public void applyStatusChangeEvent(ComplaintStatusUpdatedEvent event) {
        this.status = event.getNewStatus();
    }

	public Complaint() {}

	public Complaint(Tourist tourist, Guide guide, Tour tour, String name, String description) {
		super();
		this.tourist = tourist;
		this.guide = guide;
		this.tour = tour;
		this.name = name;
		this.description = description;
		this.status = ComplaintStatus.OnHold;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Tourist getTourist() {
		return tourist;
	}

	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
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
