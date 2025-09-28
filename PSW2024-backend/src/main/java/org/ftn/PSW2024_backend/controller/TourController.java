package org.ftn.PSW2024_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ftn.PSW2024_backend.dto.GradeDTO;
import org.ftn.PSW2024_backend.dto.KeyPointDTO;
import org.ftn.PSW2024_backend.dto.ScheduleDTO;
import org.ftn.PSW2024_backend.dto.TourDTO;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "tour")
public class TourController {

	@Autowired
	private TourService tourService;
	
    @PostMapping("/createTour")
    public ResponseEntity<Map <String, String>> scheduleTour(@RequestBody ScheduleDTO scheduleDTO) {
	Map<String, String> response = new HashMap<>();
		
		try {
			String schedule = tourService.scheduleTour(scheduleDTO);
		if(schedule.equals("timeError"))
		{
			response.put("error", "You must schedule tours at least 48 hours in advance.");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}

		response.put("success", "Tour created");
		return new ResponseEntity<>(response, HttpStatus.OK);
		 } catch (Exception e) {
		        e.printStackTrace();
		        response.put("error", "An error occurred while processing your request.");
		        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
    
    @PostMapping("/publishTour/{tourId}")
    public ResponseEntity<Map<String, String>> publishTour(@PathVariable String tourId) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String result = tourService.publishTour(tourId);
            if (result.equals("keypointError")) {
                response.put("error", "Tour must have at least 2 key points.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            response.put("success", "Tour published successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "An error occurred while processing your request.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @DeleteMapping("/cancelTour/{guideName}/{tourId}")
    public ResponseEntity<Map<String, String>> cancelTour(@PathVariable String guideName, @PathVariable String tourId) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String result = tourService.cancelTour(guideName, tourId);
            if (result.equals("timeError")) {
                response.put("error", "You can't cancel tours that are due in less than 24 hours.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            response.put("success", "Tour canceled successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "An error occurred while processing your request.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addKeypoint")
    public ResponseEntity<Map<String, String>> addKeypoint(@ModelAttribute KeyPointDTO keyPointDTO) {
        Map<String, String> response = new HashMap<>();
        
        try {
            tourService.addKeypoint(keyPointDTO);
            response.put("success", "Keypoint added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "An error ooccurred while processing your request.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getDrafts/{guide}")
    public ResponseEntity<List<TourDTO>> getDraftsByGuide(@PathVariable String guide) {
        try {
            return new ResponseEntity<>(tourService.getDraftsByGuide(guide), HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getAllByGuide/{guide}")
    public ResponseEntity<List<TourDTO>> getAllByGuide(@PathVariable String guide) {
        try {
            return new ResponseEntity<>(tourService.getAllByGuide(guide), HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getAvailable/{tourist}")
    public ResponseEntity<List<TourDTO>> getAvailable(@PathVariable String tourist) {
        try {
            return new ResponseEntity<>(tourService.getAvailableTours(tourist), HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getAwarded/{tourist}")
    public ResponseEntity<List<TourDTO>> getAwarded(@PathVariable String tourist) {
        try {
            return new ResponseEntity<>(tourService.getAwardedTours(tourist), HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/deleteTour/{guideName}/{tourId}")
    public ResponseEntity<Map <String, String>> deleteTour(@PathVariable String guideName, @PathVariable String tourId) {
    	Map<String, String> response = new HashMap<>();
        try {
            String res = tourService.cancelTour(guideName, tourId);
        	if(res.equals("forbidden"))
        			{
        				response.put("error", "You're not authorized to delete this tour");
        				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        			}
        response.put("success", "Tour deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	 e.printStackTrace();
		     response.put("error", "An error occurred while processing your request.");
		     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("/purchaseTours/{touristName}/{usePoints}")
    public ResponseEntity<Map <String, String>> reserveTours(@PathVariable String touristName, @PathVariable boolean usePoints, @RequestBody String[] tourIds) {
    	Map<String, String> response = new HashMap<>();
        try {
            String result = tourService.purchaseTours(tourIds, touristName, usePoints);
        	response.put("success", "Tours purchased");
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	 e.printStackTrace();
		     response.put("error", "An error occurred while processing your request.");
		     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/gradeTour")
    public ResponseEntity<Map <String, String>> gradeTour(@RequestBody GradeDTO gradeDTO) {
    	Map<String, String> response = new HashMap<>();
        try {
            String res = tourService.gradeTour(gradeDTO);
            if(res.equals("tooEarlyError"))
			{
				response.put("error", "You can only grade tours you've already attended");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if(res.equals("tooLateError"))
			{
				response.put("error", "You can only grade tours you've attended in last 30 days");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
        	response.put("success", "Tours purchased");
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	 e.printStackTrace();
		     response.put("error", "An error occurred while processing your request.");
		     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getPurchased/{tourist}")
    public ResponseEntity<List<TourDTO>> getAvailablePurchased(@PathVariable String tourist) {
        try {
            return new ResponseEntity<>(tourService.getPurchasedTours(tourist), HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

