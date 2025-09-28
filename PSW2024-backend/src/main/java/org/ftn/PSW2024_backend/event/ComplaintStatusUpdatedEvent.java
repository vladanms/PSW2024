package org.ftn.PSW2024_backend.event;

import java.time.LocalDateTime;


import org.ftn.PSW2024_backend.model.ComplaintStatus;
import org.springframework.stereotype.Component;

public class ComplaintStatusUpdatedEvent {

	private Long complaintId;
    private ComplaintStatus oldStatus;
    private ComplaintStatus newStatus;
    private LocalDateTime timestamp;

    public ComplaintStatusUpdatedEvent(Long complaintId, ComplaintStatus oldStatus, ComplaintStatus newStatus) {
        this.complaintId = complaintId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.timestamp = LocalDateTime.now();
    }
    
    public ComplaintStatusUpdatedEvent toDomainEvent() {
        return new ComplaintStatusUpdatedEvent(
            this.complaintId,
            this.oldStatus,
            this.newStatus
        );
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
    
    

    public ComplaintStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(ComplaintStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	public ComplaintStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(ComplaintStatus newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}