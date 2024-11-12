package swp391.reportservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391.entity.GenericTicket;

import java.util.Date;

public interface GenericTicketRepository extends JpaRepository<GenericTicket, Long> {

    @Query(" UPDATE GenericTicket g SET g.expiredDateTime = :dateExpired WHERE g.id = :id")
    @Modifying
    @Transactional
    void updateDateExpiredById(@Param("id") Long id, @Param("dateExpired") Date dateExpired);
}
