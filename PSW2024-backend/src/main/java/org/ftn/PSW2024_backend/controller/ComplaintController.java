package org.ftn.PSW2024_backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.ftn.PSW2024_backend.dto.ComplaintDTO;
import org.ftn.PSW2024_backend.dto.FileComplaintDTO;
import org.ftn.PSW2024_backend.dto.TourDTO;
import org.ftn.PSW2024_backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "complaint")
public class ComplaintController {

	
	@Autowired
	private ComplaintService complaintService;
	

	@PostMapping("/createComplaint")
	public ResponseEntity<Map<String, String>> register(@RequestBody FileComplaintDTO complaintDTO) throws MessagingException, UnsupportedEncodingException
	{

		Map<String, String> response = new HashMap<>();
		
		try {
			String res = complaintService.createComplaint(complaintDTO);

		response.put("success", "Complaint filed");
		return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing your request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	 @GetMapping("/getByGuide/{guide}")
	 public ResponseEntity<List<ComplaintDTO>> getByGuide(@PathVariable String guide) {
	        try {
	            return new ResponseEntity<>(complaintService.findAllByGuide(guide), HttpStatus.OK); 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @GetMapping("/getByStatus/{status}")
	 public ResponseEntity<List<ComplaintDTO>> getByStatus(@PathVariable String status) {
	        try {
	            return new ResponseEntity<>(complaintService.findAllByStatus(status), HttpStatus.OK); 
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @PostMapping("/changeStatus/{complaintId}/{status}")
	 public ResponseEntity<Map<String, String>> changeStatus(@PathVariable String complaintId, @PathVariable String status) {
	        Map<String, String> response = new HashMap<>();        
	        try {
	            String result = complaintService.updateStatus(complaintId, status);

	            response.put("success", "Succesfully updated.");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("error", "An error occurred while processing your request.");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
