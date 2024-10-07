package swp391.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.ReportType;

@Repository
public interface ReportTypeRepository extends JpaRepository<ReportType, Integer> {
}
