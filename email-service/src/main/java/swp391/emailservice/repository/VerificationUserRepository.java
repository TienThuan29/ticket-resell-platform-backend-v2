package swp391.emailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.VerificationUser;

@Repository
public interface VerificationUserRepository extends JpaRepository<VerificationUser, Long> {
}
