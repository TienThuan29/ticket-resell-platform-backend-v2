package swp391.paymentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.entity.Transaction;
import swp391.entity.User;
import swp391.entity.fixed.TransactionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionNo(String transactionNo);

    List<Transaction> findByUserAndType(User user, TransactionType type);
}
