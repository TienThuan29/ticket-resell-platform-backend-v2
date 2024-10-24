package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.entity.Staff;
import swp391.entity.fixed.Role;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(
            "SELECT s1 FROM Staff s1 WHERE s1.id IN " +
            "( " +
            "SELECT s.id FROM Staff s LEFT JOIN Ticket t ON t.verifyStaff.id = s.id " +
            "WHERE s.roleCode != 'ADMIN' GROUP BY s.id " +
            "ORDER BY COUNT(t.id) ASC LIMIT 1 " +
            ") AND s1.isEnable = true"
    )
    Optional<Staff> getStaffHasMinTicket();

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.balance = s.balance + ?1 WHERE s.roleCode = 'ADMIN'")
    void updateBalanceOfAdmin(Long amount);

    Optional<Staff> findByRoleCode(Role roleCode);
}
