package com.major.backend.service;

import com.major.backend.exceptions.AmountLessThanEqualToZeroException;
import com.major.backend.exceptions.InsufficientBalanceException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.Transaction;
import com.major.backend.models.User;
import com.major.backend.repositories.TransactionRepository;
import com.major.backend.repositories.UserRepository;
import com.major.backend.services.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Test
    void getAll() throws Exception
    {
        Transaction transaction1 = new Transaction("9935733559","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction2 = new Transaction("1234567890","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction3 = new Transaction("9876543210","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        List<Transaction> transactions = List.of(transaction1,transaction2,transaction3);

        when(transactionRepository.findAll()).thenReturn(transactions);
        List<Transaction> transactions1 = transactionService.findAllTransactions();
        //System.out.println(users1);
        //System.out.println(users);
        assertEquals(transactions,transactions1);
    }

//    @Test
//    void getbyMobile() throws Exception
//    {
//        Transaction transaction1 = new Transaction("9935733559","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
//
//
//
//        when(transactionRepository.findByMobileNo("123456789")).thenReturn(transaction1);
//        List<Transaction> transaction2 = transactionService.findTransactionsByMobileNo("123456789");
//        //System.out.println(users1);
//        //System.out.println(users);
//        assertEquals(transaction1,transaction2);
//    }

    @Test
    void accountBalance() throws Exception
    {
        User account = new User("Aryan","9935733559","abc123");
        when(userRepository.findByMobileNo("9935733559")).thenReturn(account);
        assertNotNull(account);
        Double balance = transactionService.getAccountBalance("9935733559");
        assertEquals(balance,account.getBalance());
    }

    @Test
    void accountAmount() throws Exception
    {
        User account = new User("Aryan","9935733559","abc123");
        when(userRepository.findByMobileNo("9935733559")).thenReturn(account);
        assertNotNull(account);
        Double amount = transactionService.getLastTransaction("9935733559");
        assertEquals(amount,account.getLasttransactionamount());
    }


    @Test
    void depositTest() throws Exception{
        User user = new User("Aryan","9935733559","abc123",100.0,20.0);
        Transaction transaction1 = new Transaction("9935733559","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        when(userRepository.findByMobileNo("9935733559")).thenReturn(user);
        assertNotNull(user);
        assertEquals("deposit", transaction1.getType());
        Double currentBalance = user.getBalance();
        user.setBalance(currentBalance + user.getLasttransactionamount());
        String tid = transactionService.deposit("9935733559",20.0,"deposit");
    }

    @Test
    public void testTransfer() throws WalletDoesNotExistException, InsufficientBalanceException, AmountLessThanEqualToZeroException {
        // create two users with some initial balance
        User user1 = new User("Aryan","1234567890","abc123",500.0,20.0);
        User user2 = new User("Aryan","9876543210","abc123",100.0,20.0);
        when(userRepository.findByMobileNo("1234567890")).thenReturn(user1);
        assertNotNull(user1);
        when(userRepository.findByMobileNo("9876543210")).thenReturn(user2);
        assertNotNull(user2);
        // transfer 100 from user1 to user2
        String tid = transactionService.transfer("1234567890", "9876543210", 100.0);

        // verify that balances are updated correctly
        User updatedUser1 = userRepository.findByMobileNo("1234567890");
        User updatedUser2 = userRepository.findByMobileNo("9876543210");

        assertEquals(400.0, updatedUser1.getBalance(), 0.001);
        assertEquals(200.0, updatedUser2.getBalance(), 0.001);

        // verify that transaction entry is made for both accounts
        List<Transaction> user1Transactions = transactionRepository.findByMobileNo("1234567890");
        List<Transaction> user2Transactions = transactionRepository.findByMobileNo("9876543210");
    }


    ArrayList<Transaction> createTransactions()
    {

        Transaction transaction1 = new Transaction("9935733559","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction2 = new Transaction("9935733559","deposit",new Date(),4.0,20.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction3 = new Transaction("9935733559","deposit",new Date(),20.0,24.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction4 = new Transaction("9935733559","deposit",new Date(),4.0,44.0, "Constants.CASHBACK_DESCRIPTION");
        ArrayList<Transaction> transactions =new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        //ArrayList. (transaction1,transaction2,transaction3,transaction4);
        return transactions;
    }

    @Test
    public void testDepositInvalidMobileNo() {
        String mobileNo = "1234567890";
        Double amount = 500.0;
        String type = "DEPOSIT";
        when(userRepository.findByMobileNo(mobileNo)).thenReturn(null);
        Assertions.assertThrows(WalletDoesNotExistException.class, () -> {
            transactionService.deposit(mobileNo, amount, type);
        });
    }






}
