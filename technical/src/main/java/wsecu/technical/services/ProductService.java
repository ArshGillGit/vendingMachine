package wsecu.technical.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import wsecu.technical.repositories.ProductRepository;
import wsecu.technical.models.Product;


/*
 * This class contains business logic for managing products
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
}
