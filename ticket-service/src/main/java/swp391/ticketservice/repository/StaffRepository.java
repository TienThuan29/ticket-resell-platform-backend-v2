package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
