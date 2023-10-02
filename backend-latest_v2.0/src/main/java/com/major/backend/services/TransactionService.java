package com.major.backend.services;

import com.major.backend.exceptions.AmountLessThanEqualToZeroException;
import com.major.backend.exceptions.InsufficientBalanceException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.Transaction;

import java.util.List;

public interface TransactionService {

    String makeEntryInTransaction(String typeOfTransaction, String emailID, Double amount, Double balance, String description);

    public List<Transaction> findTransactionsByMobileNo(String mobileNo);

    public List<Transaction> findAllTransactions();

    public Double getAccountBalance(String mobileNo) throws WalletDoesNotExistException;

    public Double getLastTransaction(String mobileNo) throws WalletDoesNotExistException;

    public String deposit(String mobileNo, Double amount, String type) throws WalletDoesNotExistException, AmountLessThanEqualToZeroException;

    public String transfer(String fromMobileNo, String toMobileNo, Double lasttransactionamount) throws WalletDoesNotExistException,
            InsufficientBalanceException, AmountLessThanEqualToZeroException;
}
