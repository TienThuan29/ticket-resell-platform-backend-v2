package swp391.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.ReportFraud;
import swp391.entity.Ticket;
import swp391.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportFraudRepository extends JpaRepository<ReportFraud, Long> {

    List<ReportFraud> findByAccuser(User accuser);

    List<ReportFraud> findByStaffId(Long id);

    Optional<ReportFraud> findByTicket(Ticket ticket);
}
