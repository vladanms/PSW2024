package org.ftn.PSW2024_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.ftn.PSW2024_backend.dto.ScheduleDTO;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class TourController {

	@Autowired
	private TourService tourService;
	
    @PostMapping("/scheduleTour")
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
    
    @PostMapping("/publishTour")
    public ResponseEntity<Map<String, String>> publishTour(@RequestBody Tour tour) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String result = tourService.publishTour(tour);
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
    

    @DeleteMapping("/cancelTour")
    public ResponseEntity<Map<String, String>> cancelTour(@RequestBody Tour tour) {
        Map<String, String> response = new HashMap<>();
        
        try {
            String result = tourService.cancelTour(tour);
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
    public ResponseEntity<Map<String, String>> addKeypoint(@RequestParam Long tourId, @RequestBody KeyPoint keyPoint) {
        Map<String, String> response = new HashMap<>();
        
        try {
            tourService.addKeypoint(tourId, keyPoint);
            response.put("success", "Keypoint added successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "An error ooccurred while processing your request.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

