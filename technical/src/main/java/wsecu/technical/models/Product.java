package wsecu.technical.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter @Setter //Lombok library auto generates getters/setters
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double cost;
    private int count;


    public Product() {} //no-arg constructor

    public Product(String theName, Double theCost, int theCount) {
        name = theName;
        cost = theCost;
        count = theCount;
    }

}