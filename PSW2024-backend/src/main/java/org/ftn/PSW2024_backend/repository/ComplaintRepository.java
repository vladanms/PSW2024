package org.ftn.PSW2024_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

import org.ftn.PSW2024_backend.model.Complaint;
import org.ftn.PSW2024_backend.model.ComplaintStatus;
import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.model.Guide;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

	List<Complaint> findAll();
	Optional<Complaint> findById(Long id);
	List<Complaint> findAllByGuide(Guide guide);
	List<Complaint> findAllByTourist(Tourist tourist);

}
