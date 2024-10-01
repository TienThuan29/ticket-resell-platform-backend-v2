package swp391.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.entity.User;
import java.util.Optional;

/**
 * Author: Nguyen Tien Thuan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username =:username AND u.isEnable = true")
    Optional<User> findEnableAccount(String username);

    Optional<User> findByCustomerCode(String customerCode);

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isSeller = true WHERE u.id = :id")
    void updateIsSellerById(@Param("id") Long id);

}
