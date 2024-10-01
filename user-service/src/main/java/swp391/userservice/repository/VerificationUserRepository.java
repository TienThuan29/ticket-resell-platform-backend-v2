package swp391.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.entity.VerificationUser;

import java.util.Optional;

public interface VerificationUserRepository extends JpaRepository<VerificationUser, Long> {

    Optional<VerificationUser> findByVerificationCode(String code);
}
