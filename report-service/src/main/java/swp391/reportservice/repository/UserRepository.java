package swp391.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
