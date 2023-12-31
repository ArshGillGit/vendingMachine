package wsecu.technical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import wsecu.technical.services.TransactionService;
import wsecu.technical.models.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction theTransaction) {
        try {
            Transaction transaction = transactionService.saveTransaction(theTransaction);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long theId) {
        return transactionService.getSingleTransaction(theId);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransaction();
    }
    
}
