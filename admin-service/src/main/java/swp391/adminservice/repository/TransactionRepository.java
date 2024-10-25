package swp391.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swp391.entity.Transaction;
import swp391.entity.fixed.TransactionType;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT sum(t.amount) from Transaction t where t.type =:type and t.isDone = true")
    Long sumTheAmountByTransactionType(TransactionType type);
}
