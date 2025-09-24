package org.ftn.PSW2024_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.ftn.PSW2024_backend.model.Tour;

public interface TourRepository extends JpaRepository<Tour, Long>{

	List<Tour> FindAll();
	Tour FindById(Long id);
	List<Tour> findByGuide(String guide);
	List<Tour> findByGuideAndIsPublishedFalse(String guide);
	List<Tour> findByGuideAndIsPublishedTrue(String guide);
	List<Tour> findByCategory(String category);
}
