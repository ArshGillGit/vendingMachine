package wsecu.technical.models;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Getter @Setter //lombok auto generates getters/setters
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalCost;

    private ZonedDateTime dateTime;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProduct> transactionProducts;

    //NOTE: no-arg constructor is auto-generated

    protected void onCreate() {
        dateTime = ZonedDateTime.now();
    }


}