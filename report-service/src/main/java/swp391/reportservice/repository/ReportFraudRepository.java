package swp391.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.ReportFraud;
import swp391.entity.User;

import java.util.List;

@Repository
public interface ReportFraudRepository extends JpaRepository<ReportFraud, Long> {

    List<ReportFraud> findByAccuser(User accuser);

    List<ReportFraud> findByStaffId(Long id);
}
