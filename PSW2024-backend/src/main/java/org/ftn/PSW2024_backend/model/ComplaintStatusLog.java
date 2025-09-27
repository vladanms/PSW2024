package org.ftn.PSW2024_backend.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ftn.PSW2024_backend.event.ComplaintStatusUpdatedEvent;

@Entity
@Table(name = "complaint_status_events")
public class ComplaintStatusLog {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "complaint_id", nullable = false)
	private Long complaintId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "old_status", nullable = false)
    private ComplaintStatus oldStatus;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private ComplaintStatus newStatus;
	
	 @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    
    public ComplaintStatusLog() {}

    public ComplaintStatusLog(Long complaintId, ComplaintStatus oldStatus, ComplaintStatus newStatus) {
    	this.complaintId = complaintId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.timestamp = LocalDateTime.now();
    }  
    
    public ComplaintStatusUpdatedEvent toDomainEvent() {
        return new ComplaintStatusUpdatedEvent(complaintId, oldStatus, newStatus);
    }
    
	public Long getId() {
		return id;
	}

	public Long getComplaintId() {
		return complaintId;
	}
	public ComplaintStatus getOldStatus() {
		return oldStatus;
	}

	public ComplaintStatus getNewStatus() {
		return newStatus;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}  
    
}
