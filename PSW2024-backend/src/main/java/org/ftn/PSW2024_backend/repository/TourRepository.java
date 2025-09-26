package org.ftn.PSW2024_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

import org.ftn.PSW2024_backend.model.User;
import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.model.Tourist;

public interface TourRepository extends JpaRepository<Tour, Long>{

	List<Tour> findAll();
	Optional<Tour> findById(Long id);
	List<Tour> findByGuide(String guide);
	List<Tour> findByGuideAndIsPublishedFalse(User guide);
	List<Tour> findByGuideAndIsPublishedTrue(User guide);
	List<Tour> findByCategory(String category);

}
