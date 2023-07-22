package wsecu.technical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

import wsecu.technical.repositories.ProductRepository;
import wsecu.technical.repositories.TransactionProductRepository;
import wsecu.technical.repositories.TransactionRepository;
import wsecu.technical.models.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionProductRepository transactionProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll(); //NOTE: Hibernate will auto join the tables 
    }

    public Transaction getSingleTransaction(Long theId) {
        if (theId != null) {
            return transactionRepository.findById(theId).orElseThrow();
        } else {
            throw new IllegalArgumentException("id cannot be null");
        }
    }

    @Transactional
    public Transaction saveTransaction(Transaction theTransaction) {
        Transaction savedTran = transactionRepository.save(theTransaction); //saving will auto generate an id in Transaction table

        //loop over each transactionProducts item and make 2 more saves
        for (TransactionProduct tp: theTransaction.getTransactionProducts()) {
            
            //check if the product has enough inventory, and if so reduce it by the amount purchased.
            Product product = productRepository.findById(tp.getId()).orElseThrow();
            if (tp.getQuantity() > 0) {
                if (product.getCount() > tp.getQuantity()) {
                    product.setCount(product.getCount() - tp.getQuantity());
                    productRepository.save((product)); //saves to Product table

                    tp.setTransactionId(savedTran.getId());
                    transactionProductRepository.save(tp); //saves to TransactionProduct (junction) table.
                } else {
                    throw new IllegalArgumentException("not enough inventory to complete this purchase");
                }
            } else {
                throw new IllegalArgumentException("cannot purchase negative amount");
            }
            
        }

        return savedTran;
    }

    
}



/* Example Json payload for POSTing a transaction:


{
    "amountPaid": 2.75,
    "transactionProducts": [
        {
            "productId": 2,
            "quantity": 3
        },
        {
            "productId": 1,
            "quantity": 1
        }
    ]
}


 */