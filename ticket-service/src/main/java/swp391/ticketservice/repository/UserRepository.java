package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
