package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.TypePolicy;

@Repository
public interface TypePolicyRepository extends JpaRepository<TypePolicy, Long> {
}
