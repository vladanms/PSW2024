package org.ftn.PSW2024_backend.event;

import java.util.List;

import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.ComplaintStatusLog;
import org.ftn.PSW2024_backend.repository.ComplaintRepository;
import org.ftn.PSW2024_backend.repository.ComplaintStatusLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ComplaintRebuilder {
    
	@Autowired
    private  ComplaintRepository complaints;
	@Autowired
    private  ComplaintStatusLogRepository logs;



    public Complaint rebuild(Long complaintId) {
    	 Complaint complaint = complaints.findById(complaintId).orElse(null);

         List<ComplaintStatusLog> eventLogs = logs.findByComplaintIdOrderByTimestampAsc(complaintId);

         for (ComplaintStatusLog log : eventLogs) {
             ComplaintStatusUpdatedEvent event = log.toDomainEvent();
             complaint.applyStatusChangeEvent(event);
         }

         return complaint;
    	}
}