package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
