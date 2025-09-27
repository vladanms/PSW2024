package org.ftn.PSW2024_backend.repository;
import java.util.List;

import org.ftn.PSW2024_backend.model.ComplaintStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintStatusLogRepository extends JpaRepository<ComplaintStatusLog, Long> {

    List<ComplaintStatusLog> findByComplaintIdOrderByTimestampAsc(Long complaintId);
}
