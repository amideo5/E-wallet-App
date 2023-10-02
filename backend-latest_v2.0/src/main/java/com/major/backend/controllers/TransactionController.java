package com.major.backend.controllers;

import com.major.backend.exceptions.AmountLessThanEqualToZeroException;
import com.major.backend.exceptions.InsufficientBalanceException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.Transaction;
import com.major.backend.models.Transfer;
import com.major.backend.models.User;
import com.major.backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@CrossOrigin(origins = "http://localhost:3000/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<?> findAllTransactions(){
        List<Transaction> transactions = transactionService.findAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/{mobileNo}")
    public ResponseEntity<?> findTransactions(@PathVariable String mobileNo){
        List<Transaction> transactions = transactionService.findTransactionsByMobileNo(mobileNo);
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/balance/{mobileNo}")
    public ResponseEntity<?> getAccountBalance(@PathVariable String mobileNo)
    {

        try{
            Double balance = transactionService.getAccountBalance(mobileNo);
            return ResponseEntity.status(HttpStatus.OK).body(balance);
        }catch (WalletDoesNotExistException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/lasttranamount/{mobileNo}")
    public ResponseEntity<?> getLastTransaction(@PathVariable String mobileNo)
    {
        try{
            Double amount = transactionService.getLastTransaction(mobileNo);
            return ResponseEntity.status(HttpStatus.OK).body(amount);
        }catch (WalletDoesNotExistException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody User user) {
        try {
            String tid = transactionService.deposit(user.getMobileNo(), user.getLasttransactionamount(), "DEPOSIT");
            return ResponseEntity.ok("Amount "+ user.getLasttransactionamount()+ " deposited successfully!! ");
        }
        catch(WalletDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (AmountLessThanEqualToZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestBody Transfer transfer) throws WalletDoesNotExistException, InsufficientBalanceException {
        try {
            String tid = transactionService.transfer(transfer.getFromMobileNo(),transfer.getToMobileNo(), transfer.getLasttransactionamount());
            return ResponseEntity.ok("Amount "+ transfer.getLasttransactionamount()+ " transferred successfully!!");
        }
        catch(WalletDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
        catch (AmountLessThanEqualToZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
