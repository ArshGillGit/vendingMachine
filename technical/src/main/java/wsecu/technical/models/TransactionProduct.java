package wsecu.technical.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter //auto generate getters/setters
public class TransactionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Long productId;

    @ManyToOne
    private Long transactionId;

    //Note: no-arg constructor is auto generated.
    
}
