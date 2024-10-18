package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Policy;
import swp391.entity.TypePolicy;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

    Optional<Policy> findByTypePolicy(TypePolicy typePolicy);
}
