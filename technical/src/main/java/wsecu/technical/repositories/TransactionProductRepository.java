package wsecu.technical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsecu.technical.models.TransactionProduct;

public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Long>{
    
}
