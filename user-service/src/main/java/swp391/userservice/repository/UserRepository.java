package swp391.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Author: Nguyen Tien Thuan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username =:username")
    Optional<User> findEnableAccount(String username);

    Optional<User> findByCustomerCode(String customerCode);

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isSeller = true WHERE u.id = :id")
    void updateIsSellerById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstname, ' ', u.lastname) LIKE %:name%")
    List<User> findByName(String name);

    @Query("SELECT DISTINCT gt.seller.id FROM User u " +
            "INNER JOIN OrderTicket ot ON u.id = ot.buyer.id " +
            "INNER JOIN GenericTicket gt ON ot.genericTicket.id = gt.id " +
            "WHERE u.id = :userId " +
            "UNION " +
            "SELECT DISTINCT t.buyerId FROM User u " +
            "INNER JOIN GenericTicket gt ON gt.seller.id = u.id " +
            "INNER JOIN Ticket t ON t.genericTicket.id = gt.id " +
            "WHERE gt.seller.id = :userId")
    List<Long> findUsersIdBoxChat(Long userId);

    @Query("SELECT DISTINCT u FROM User u WHERE u.id IN :userIds")
    List<User> findUsersBoxChat(List<Long> userIds);
}
