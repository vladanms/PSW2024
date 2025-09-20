package org.ftn.PSW2024_backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ftn.PSW2024_backend.dto.ScheduleDTO;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TourService {

	@Autowired
	private TourRepository tours;
	
	public String scheduleTour(ScheduleDTO scheduleDTO)
	{
		if (scheduleDTO.getTime().isBefore(LocalDateTime.now().plusHours(48))) {
            return "timeError";
        }
		
		 Tour tour = new Tour(
		            scheduleDTO.getName(),
		            scheduleDTO.getDescription(),
		            scheduleDTO.getDifficulty(),
		            scheduleDTO.getPrice(),
		            scheduleDTO.getGuideName(),
		            scheduleDTO.getTime()
		        );
		 tours.save(tour);
		return "success";
	}
	
	public String publishTour(Tour tour)
	{
		if(tour.getKeyPoints().size() < 2) {
			return "keypointError";
		}
		
		tour.setPublished(true);
		tours.save(tour);
		return "success";
	}
	
	
	public String cancelTour(Tour tour)
	{
		if (tour.getTime().isBefore(LocalDateTime.now().plusHours(24))) {
            return "timeError";
        }
		
		tours.delete(tour);
		return "success";
	}
	
	public void addKeypoint(Long tourID, KeyPoint keypoint)
	{
		Tour tour = tours.FindById(tourID);
		List<KeyPoint> keypoints = tour.getKeyPoints();
		keypoints.add(keypoint);
		tour.setKeyPoints(keypoints);
		tours.save(tour);
	}
	
	public ArrayList<Tour> getToursByGuide(String guide)
	{
		return (ArrayList<Tour>) tours.findByGuide(guide);
	}
}
