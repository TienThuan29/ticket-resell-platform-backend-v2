package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("SELECT pm FROM PaymentMethod pm WHERE pm.isDeleted = false")
    List<PaymentMethod> getAllMethodNotDeleted();

}
