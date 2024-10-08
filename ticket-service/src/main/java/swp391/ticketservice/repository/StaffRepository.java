package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Staff;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(
            "SELECT s1 FROM Staff s1 WHERE s1.id IN " +
            "( " +
            "SELECT s.id FROM Staff s LEFT JOIN Ticket t ON t.verifyStaff.id = s.id " +
            "WHERE s.roleCode != 'ADMIN' GROUP BY s.id " +
            "ORDER BY COUNT(t.id) ASC LIMIT 1 " +
            ")"
    )
    Optional<Staff> getStaffHasMinTicket();

}
