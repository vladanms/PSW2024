package org.ftn.PSW2024_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ftn.PSW2024_backend.dto.KeyPointDTO;
import org.ftn.PSW2024_backend.dto.ScheduleDTO;
import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.Grade;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.dto.TourDTO;
import org.ftn.PSW2024_backend.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class TourService {

	@Autowired
	private TourRepository tours;
	
	@Value("${image.directory:images}")
	private String imageDirectory;
	
	public String scheduleTour(ScheduleDTO scheduleDTO)
	{
		if (scheduleDTO.getTime().isBefore(LocalDateTime.now().plusHours(48))) {
            return "timeError";
        }
		
		 Tour tour = new Tour(
		            scheduleDTO.getName(),
		            scheduleDTO.getDescription(),
		            scheduleDTO.getCategory(),
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
	
	public String addKeypoint(KeyPointDTO keyPointDTO) throws IOException
	{
		Tour tour = tours.FindById(Long.parseLong(keyPointDTO.getTourId()));
		MultipartFile image = keyPointDTO.getImage();
		String imageName = tour.getId() + "-" + tour.getKeyPoints().size();
		
	    String format = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
	    if(!format.equals(".jpg") && !format.equals(".png"))
	    {
		return("formatError");
	    }
		Files.copy(image.getInputStream(), Paths.get(imageDirectory, imageName + format), StandardCopyOption.REPLACE_EXISTING);
		String imagePath = (Paths.get(imageDirectory, imageName + format)).toString();
		
		KeyPoint keypoint = new KeyPoint(keyPointDTO.getName(), keyPointDTO.getDescription(), imagePath, Float.parseFloat(keyPointDTO.getLongitude()), 
				Float.parseFloat(keyPointDTO.getLatitude()));
		
		List<KeyPoint> keypoints = tour.getKeyPoints();
		keypoints.add(keypoint);
		tour.setKeyPoints(keypoints);
		tours.save(tour);
		
		return("success");
	}
	
	public ArrayList<Tour> getToursByGuide(String guide)
	{
		return (ArrayList<Tour>) tours.findByGuide(guide);
	}
	
	public List<TourDTO> getDraftsByGuide(String guide)
	{
		List<TourDTO> drafts = new ArrayList<TourDTO>();
		
		for( Tour tour : ((ArrayList<Tour>) tours.findByGuideAndIsPublishedFalse(guide)))
		{
			List<String> touristNames = new ArrayList<String>();
			for(Tourist t : tour.getTourists())
			{
				touristNames.add(t.getUsername());
			}
			
			TourDTO dto = new TourDTO(
					tour.getId(),
					tour.getName(),
					tour.getDescription(),
					tour.getCategory(),
					tour.getDifficulty(),
					tour.getPrice(),
					tour.getTime(),
					tour.getGuide(),
					touristNames,
				    tour.getKeyPoints(),
				    tour.getComplaints(),
				    tour.isPublished(),
				    tour.getGrades()
			);
			drafts.add(dto);
		}
		return drafts;
	}
	
	public ArrayList<Tour> getPublishedByGuide(String guide)
	{
		return (ArrayList<Tour>) tours.findByGuideAndIsPublishedTrue(guide);
	}
	
	public ArrayList<Tour> getPublishedByCategory(String category)
	{
		return (ArrayList<Tour>) tours.findByCategory(category);
	}
}
