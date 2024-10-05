package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Staff;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT st FROM Staff st WHERE st.username =:username AND st.isEnable = true")
    Optional<Staff> findEnableAccount(String username);

    Optional<Staff> findByStaffCode(String staffCode);

    Optional<Staff> findByUsername(String username);

    Optional<Staff> findByEmail(String email);
}
