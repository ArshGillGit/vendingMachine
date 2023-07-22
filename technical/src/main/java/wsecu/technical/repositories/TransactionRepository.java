package wsecu.technical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsecu.technical.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
}
