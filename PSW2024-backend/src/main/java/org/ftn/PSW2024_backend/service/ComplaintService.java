package org.ftn.PSW2024_backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.ftn.PSW2024_backend.dto.ComplaintDTO;
import org.ftn.PSW2024_backend.dto.FileComplaintDTO;
import org.ftn.PSW2024_backend.event.ComplaintStatusUpdatedEvent;
import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.ComplaintStatus;
import org.ftn.PSW2024_backend.model.ComplaintStatusLog;
import org.ftn.PSW2024_backend.model.Guide;
import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.repository.ComplaintRepository;
import org.ftn.PSW2024_backend.repository.ComplaintStatusLogRepository;
import org.ftn.PSW2024_backend.repository.TourRepository;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaints;
	
	@Autowired
	private ComplaintStatusLogRepository logs;
	
	@Autowired
	private TourRepository tours;
	
	@Autowired
	private UserRepository users;
	
	public String createComplaint(FileComplaintDTO complaintDTO) 
	{
	     Complaint complaint = new Complaint( 
	        (Tourist) users.findByUsername(complaintDTO.getTourist()),
	        (Guide) users.findByUsername(complaintDTO.getGuide()),
	        tours.findById(Long.parseLong(complaintDTO.getTourId())).orElse(null),
	       	complaintDTO.getName(),
	       	complaintDTO.getDescription()
	        );
	        	        
	        complaints.save(complaint); 
	        return "success";
	}
	   
	@Transactional
    public String updateStatus(String complaintId, String status) {

        Complaint complaint = complaints.findById(Long.parseLong(complaintId)).orElse(null);
        if(complaint == null)
        {
        	return "error";
        }
        ComplaintStatusUpdatedEvent event = complaint.updateStatus(ComplaintStatus.valueOf(status));
        
        if(status.equals("Rejected"))
        {
        	Tourist tourist = complaint.getTourist();
        	int penaltyPoints = tourist.getPenaltyPoints()+1;
        	tourist.setPenaltyPoints(penaltyPoints);
        	if(penaltyPoints > 9)
        	{
        		tourist.setMalicious(true);
        	}
        	users.save(tourist);
        }

        ComplaintStatusLog log = new ComplaintStatusLog(event.getComplaintId(),event.getOldStatus(),event.getNewStatus() );
        logs.save(log);
        complaints.save(complaint);

        return "success";
    }
	
	public List<ComplaintDTO> findAllByGuide(String guideName)
	{
		List<ComplaintDTO> complaintDTOs = new ArrayList<ComplaintDTO>();
		
		for(Complaint complaint : complaints.findAllByGuide((Guide) users.findByUsername(guideName)))
		{
			ComplaintDTO dto = new ComplaintDTO(
					complaint.getId().toString(),
					complaint.getTourist().getUsername(),
					complaint.getGuide().getUsername(),
					complaint.getName(),
					complaint.getStatus().toString(),
					complaint.getDescription(),
					complaint.getStatus().toString()
					);
			
			complaintDTOs.add(dto);
		}
		return complaintDTOs;
	}
	
	public List<ComplaintDTO> findAllByStatus(String status)
	{
		List<ComplaintDTO> complaintDTOs = new ArrayList<ComplaintDTO>();
		
	    for (Complaint complaint : complaints.findAll()) {
	        List<ComplaintStatusLog> statusLogs = logs.findByComplaintIdOrderByTimestampAsc(complaint.getId());

	        for (ComplaintStatusLog log : statusLogs) {
	            complaint.applyStatusChangeEvent(log.toDomainEvent());
	        }

	        if (complaint.getStatus() == ComplaintStatus.valueOf(status)) {
			ComplaintDTO dto = new ComplaintDTO(
					complaint.getId().toString(),
					complaint.getTourist().getUsername(),
					complaint.getGuide().getUsername(),
					complaint.getName(),
					complaint.getStatus().toString(),
					complaint.getDescription(),
					complaint.getStatus().toString()
					);
			
			complaintDTOs.add(dto);
	        }
	    }
		return complaintDTOs;
	}
}
