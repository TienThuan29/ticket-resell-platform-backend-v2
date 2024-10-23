package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swp391.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u From User u")
    List<User> getAllUsers();
}
