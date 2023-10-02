package com.major.backend.model;

import com.major.backend.models.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    @Test
    public void testGettersAndSetters() {
        String transactionId = "12345";
        String mobileNo = "9876543210";
        String type = "DEBIT";
        Date timestamp = new Date();
        Double amount = 100.0;
        Double balance = 500.0;
        String description = "Payment for order #123";

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setmobileNo(mobileNo);
        transaction.setType(type);
        transaction.setTimestamp(timestamp);
        transaction.setAmount(amount);
        transaction.setBalance(balance);
        transaction.setDescription(description);

        assertEquals(transactionId, transaction.getTransactionId());
        assertEquals(mobileNo, transaction.getmobileNo());
        assertEquals(type, transaction.getType());
        assertEquals(timestamp, transaction.getTimestamp());
        assertEquals(amount, transaction.getAmount(), 0.001);
        assertEquals(balance, transaction.getBalance(), 0.001);
        assertEquals(description, transaction.getDescription());
    }

    @Test
    public void testConstructor() {
        String mobileNo = "9876543210";
        String type = "DEBIT";
        Date timestamp = new Date();
        Double amount = 100.0;
        Double balance = 500.0;
        String description = "Payment for order #123";

        Transaction transaction = new Transaction(mobileNo, type, timestamp, amount, balance, description);

        assertNull(transaction.getTransactionId());
        assertEquals(mobileNo, transaction.getmobileNo());
        assertEquals(type, transaction.getType());
        assertEquals(timestamp, transaction.getTimestamp());
        assertEquals(amount, transaction.getAmount(), 0.001);
        assertEquals(balance, transaction.getBalance(), 0.001);
        assertEquals(description, transaction.getDescription());
    }
}
