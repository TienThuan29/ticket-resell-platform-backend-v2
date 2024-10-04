package swp391.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.Token;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(
            "SELECT t from Token t " +
                    "INNER JOIN User u " +
                    "ON t.user.id = u.id " +
                    "WHERE u.id =:userId AND (t.expired = false OR t.revoked = false)"
    )
    List<Token> findAllValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);

}
