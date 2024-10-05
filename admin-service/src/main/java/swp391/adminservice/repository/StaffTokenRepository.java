package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.StaffToken;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffTokenRepository extends JpaRepository<StaffToken, Long> {

    @Query(
            "SELECT st from StaffToken st " +
                    "INNER JOIN Staff s " +
                    "ON st.staff.id = s.id " +
                    "WHERE s.id =:staffId AND (st.expired = false OR st.revoked = false)"
    )
    List<StaffToken> findAllValidTokenByUser(Long staffId);

    Optional<StaffToken> findByToken(String token);

}
