package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.entity.GenericTicket;
import swp391.entity.Transaction;
import swp391.entity.User;
import swp391.entity.fixed.TransactionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionNo(String transactionNo);

    List<Transaction> findByUserAndType(User user, TransactionType type);

    @Query("SELECT tr FROM GenericTicket gt " +
            "INNER JOIN User u ON gt.seller.id = u.id " +
            "INNER JOIN Transaction tr on tr.user.id=u.id " +
            "WHERE gt IN :genericTickets and tr.type = 'SELLING' and tr.isDone = FALSE ")
    List<Transaction> getByGenericTickets(List<GenericTicket> genericTickets);
}
