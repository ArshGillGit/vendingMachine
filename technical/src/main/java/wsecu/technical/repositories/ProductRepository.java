package wsecu.technical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wsecu.technical.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
