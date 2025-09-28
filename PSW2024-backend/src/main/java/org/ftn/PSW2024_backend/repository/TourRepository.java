package org.ftn.PSW2024_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.ftn.PSW2024_backend.model.User;
import org.ftn.PSW2024_backend.model.Guide;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.model.Tourist;

public interface TourRepository extends JpaRepository<Tour, Long>{

	List<Tour> findAll();
	Optional<Tour> findById(Long id);
	List<Tour> findAllByGuide(Guide guide);
	List<Tour> findByGuideAndIsPublishedFalse(Guide guide);
	List<Tour> findByGuideAndIsPublishedTrue(Guide guide);
	List<Tour> findByCategory(String category);
	List<Tour> findByGuideAndTimeAfter(Guide guide, LocalDateTime time);
}
