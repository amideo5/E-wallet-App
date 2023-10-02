package com.major.backend.controller;

import com.major.backend.models.Transaction;
import com.major.backend.models.User;
import com.major.backend.services.TransactionService;
import com.major.backend.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Test
    void getAllTransactions() throws Exception
    {
        Transaction transaction1 = new Transaction("9935733559","deposit",new Date(),20.0,0.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction2 = new Transaction("9935733559","deposit",new Date(),4.0,20.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction3 = new Transaction("9935733559","deposit",new Date(),20.0,24.0, "Constants.DEPOSIT_DESCRIPTION");
        Transaction transaction4 = new Transaction("9935733559","deposit",new Date(),4.0,44.0, "Constants.CASHBACK_DESCRIPTION");

        List<Transaction> transactions = List.of(transaction1,transaction2, transaction3, transaction4);

        Mockito.when(transactionService.findAllTransactions()).thenReturn(transactions);
        List<Transaction> transactions1 = transactionService.findAllTransactions();
        assertEquals(transactions1,transactions);

    }

}
