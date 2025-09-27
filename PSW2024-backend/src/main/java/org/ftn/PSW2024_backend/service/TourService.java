package org.ftn.PSW2024_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.ftn.PSW2024_backend.dto.ComplaintDTO;
import org.ftn.PSW2024_backend.dto.GradeDTO;
import org.ftn.PSW2024_backend.dto.KeyPointDTO;
import org.ftn.PSW2024_backend.dto.ScheduleDTO;
import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.Grade;
import org.ftn.PSW2024_backend.model.KeyPoint;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.dto.TourDTO;
import org.ftn.PSW2024_backend.repository.TourRepository;
import org.ftn.PSW2024_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import org.ftn.PSW2024_backend.model.Guide;
import org.ftn.PSW2024_backend.model.User;

@Service
public class TourService {

	@Autowired
	private TourRepository tours;
	
	@Autowired
	private UserRepository users;
	
	@Value("${image.directory:images}")
	private String imageDirectory;
	public ArrayList<Tour> getToursByGuide(String guide)
	{
		return (ArrayList<Tour>) tours.findByGuide(guide);
	}

//GUIDE FUNCTIONS===========================================================================		
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
		            (Guide) users.findByUsername(scheduleDTO.getGuideName()),
		            scheduleDTO.getTime()
		        );
		 tours.save(tour);
		return "success";
	}
	
	public String publishTour(String tourId)
	{
		Tour tour = tours.findById(Long.parseLong(tourId)).orElse(null);
		
		if(tour.getKeyPoints().size() < 2) {
			return "keypointError";
		}
		
		tour.setPublished(true);
		tours.save(tour);
		return "success";
	}
	
	
	public String addKeypoint(KeyPointDTO keyPointDTO) throws IOException
	{
		Tour tour  = tours.findById(Long.parseLong(keyPointDTO.getTourId())).orElse(null);
		MultipartFile image = keyPointDTO.getImage();
		String imageName = "img" + tour.getId() + "-" + tour.getKeyPoints().size();
		
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
	
	
	
	
	public List<TourDTO> getDraftsByGuide(String guide)
	{
		List<TourDTO> drafts = new ArrayList<TourDTO>();
		
		for( Tour tour : ((ArrayList<Tour>) tours.findByGuideAndIsPublishedFalse((Guide) users.findByUsername(guide))))
		{
			List<String> touristNames = new ArrayList<String>();
			for(User t : tour.getTourists())
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
					tour.getGuide().getUsername(),
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
	
	public String cancelTour(String guideName, String tourId)
	{
		Tour tour = tours.findById(Long.parseLong(tourId)).orElse(null);

		if(!tour.getGuide().getName().equals(guideName))
		{
			return "forbidden";
		}
		
		Guide guide = (Guide) users.findByUsername(guideName);
		
		if(!tour.isPublished())
		{
			tours.delete(tour);
			return "success";
		}
		
		if(tour.getTime().isBefore(LocalDateTime.now().plusHours(24)))	
		{
			return "timeError";
		}
		
		
		for(Tourist tourist : tour.getTourists())
		{
			int rewardPoints = tourist.getRewardPoints() + tour.getPrice();
			tourist.setRewardPoints(rewardPoints);
			users.save(tourist);
		}
		
		tours.delete(tour);
		int penaltyPoints = guide.getPenaltyPoints()+1;
		guide.setPenaltyPoints(penaltyPoints);
		if(penaltyPoints > 9)
		{
			guide.setMalicious(true);
		}
		
		users.save(guide);	
		return "success";
	}
	
//GUIDE FUNCTIONS===========================================================================
	
//TOURIST FUNCTIONS=========================================================================
	
	public String purchaseTours(String[] tourIds, String touristName, boolean usePoints)
	{
		Tourist tourist = (Tourist) users.findByUsername(touristName);
		int price = 0;
		List<Tour> tourList = tourist.getTours();
		
		for(String id : tourIds)
		{
			Tour tour = tours.findById(Long.parseLong(id)).orElse(null);
			price = price + tour.getPrice();
			
			List<Tourist> touristList = tour.getTourists();
			touristList.add(tourist);			
			
			tourList.add(tour);
			tour.setTourists(touristList);
			
			users.save(tourist);
			tours.save(tour);
		}
	
		if(usePoints)
		{
			int rewardPoints = tourist.getRewardPoints(); 
			rewardPoints = rewardPoints-price;
			if(rewardPoints < 0)
			{
				rewardPoints = 0;
			}
			tourist.setRewardPoints(rewardPoints);
			users.save(tourist);
		}
		
		return "success";
	}
	
	public String makeComplaint(ComplaintDTO complaintDTO)
	{
		
		
		return "success";
	}

	public List<TourDTO> getAvailableTours(String touristName)
	{
	Tourist tourist = (Tourist) users.findByUsername(touristName);
	
		List<TourDTO> availableTours = new ArrayList<TourDTO>();
		
		List<Tour> tourList = tours.findAll();
		for(Tour t : tourList)
		{
			if(t.getTourists().contains(tourist))
			{
				tourList.remove(t);
			}
		}	
		
		for( Tour tour : tourList)
		{
			List<String> touristNames = new ArrayList<String>();
			for(User t : tour.getTourists())
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
					tour.getGuide().getUsername(),
					touristNames,
				    tour.getKeyPoints(),
				    tour.getComplaints(),
				    tour.isPublished(),
				    tour.getGrades()
			);
			availableTours.add(dto);
		}
		return availableTours;
	}
	
	public List<TourDTO> getPurchasedTours(String touristName)
	{
	Tourist tourist = (Tourist) users.findByUsername(touristName);
	
		List<TourDTO> purschasedTours = new ArrayList<TourDTO>();
		
		for( Tour tour : tourist.getTours())
		{
			List<String> touristNames = new ArrayList<String>();
			for(User t : tour.getTourists())
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
					tour.getGuide().getUsername(),
					touristNames,
				    tour.getKeyPoints(),
				    tour.getComplaints(),
				    tour.isPublished(),
				    tour.getGrades()
			);
			purschasedTours.add(dto);
		}
		return purschasedTours;
	}
	public String gradeTour(GradeDTO gradeDTO)
	{
		Tour tour = tours.findById(Long.parseLong(gradeDTO.getTourId())).orElse(null);
		if(tour == null)
		{
			return "error";
		}
		
		Grade grade = new Grade(gradeDTO.getTourist(), gradeDTO.getContent(), gradeDTO.getGrade());
		
		List<Grade> grades = tour.getGrades();
		grades.add(grade);
		tour.setGrades(grades);
		
		tours.save(tour);
		return "success";
	}
//TOURIST FUNCTIONS=========================================================================
	
//GRADES====================================================================================
	public Float getTourAverageGrade(Tour tour)
	{
		Float avgGrade = 0f;
		
		for(Grade grade : tour.getGrades() )
		{
			avgGrade = avgGrade + grade.getGrade();
		}
		
		avgGrade = avgGrade / tour.getGrades().size();
		
		return avgGrade;
	}
	
	public List<Tour> getBestOrWorst(Guide guide, boolean best)
	{
		List<Tour> tourList = new ArrayList<Tour>();
		Float avgGrade = 0f;
		
		for(Tour tour : guide.getTours())
		{
			if(tour.getTime().isAfter(LocalDateTime.now().minusMonths(1)) && tour.getGrades().size() > 0)
			{
				if(best)
				{
					if(getTourAverageGrade(tour) > avgGrade || avgGrade == 0f)
					{
						avgGrade = getTourAverageGrade(tour);
					}
				}
				else
				{

					if(getTourAverageGrade(tour) < avgGrade || avgGrade == 0f)
					{
						avgGrade = getTourAverageGrade(tour);
					}
				}
			}
		}
		for(Tour tour : guide.getTours())
		{
			if(getTourAverageGrade(tour).equals(avgGrade))
			{
				tourList.add(tour);
			}
		}
		
		return tourList;
	}
	
	public List<Tour> getMostPopular(Guide guide)
	{
		List<Tour> tourList = new ArrayList<Tour>();
		int maxCustomers = 0;
		
		for(Tour tour : guide.getTours())
		{
			if(tour.getTourists().size() > maxCustomers)
			{
				maxCustomers = tour.getTourists().size();
			}
		}
		
		for(Tour tour : guide.getTours())
		{
			if(tour.getTourists().size() == maxCustomers)
			{
				tourList.add(tour);
			}
		}
		return tourList;
	}
//GRADES====================================================================================

//QUERIES===================================================================================	
	public ArrayList<Tour> getPublishedByGuide(String guide)
	{
		return (ArrayList<Tour>) tours.findByGuideAndIsPublishedTrue((Guide) users.findByUsername(guide));
	}
	
	public ArrayList<Tour> getPublishedByCategory(String category)
	{
		return (ArrayList<Tour>) tours.findByCategory(category);
	}
//QUERIES===================================================================================	
}
