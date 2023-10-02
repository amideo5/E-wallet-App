package com.major.backend.services;

import com.major.backend.exceptions.AmountLessThanEqualToZeroException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.exceptions.InsufficientBalanceException;
import com.major.backend.models.Transaction;
import com.major.backend.models.User;
import com.major.backend.repositories.TransactionRepository;
import com.major.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String makeEntryInTransaction(String typeOfTransaction, String mobileNo, Double amount, Double balance, String description) {
        Transaction bankTransaction = new Transaction(mobileNo,typeOfTransaction, new Date(), amount, balance, description);
        transactionRepository.save(bankTransaction);
        return bankTransaction.getTransactionId();
    }

    public List<Transaction> findTransactionsByMobileNo(String mobileNo) {
        return (List<Transaction>) transactionRepository.findByMobileNo(mobileNo);
    }

    public List<Transaction> findAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public Double getAccountBalance(String mobileNo) throws WalletDoesNotExistException {

        User account = userRepository.findByMobileNo(mobileNo);
        if (account==null) {
            throw new WalletDoesNotExistException(mobileNo);
        }
        return account.getBalance();
    }

    public Double getLastTransaction(String mobileNo) throws WalletDoesNotExistException  {
        User account = userRepository.findByMobileNo(mobileNo);
        if (account==null) {
            throw new WalletDoesNotExistException(mobileNo);
        }
        return account.getLasttransactionamount();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public String deposit(String mobileNo, Double amount, String type) throws WalletDoesNotExistException, AmountLessThanEqualToZeroException {
        User user = userRepository.findByMobileNo(mobileNo);
        String tid = null;
        if (user==null) {
            throw new WalletDoesNotExistException(mobileNo);
        }
        if (amount <=0)
        {
            throw new AmountLessThanEqualToZeroException();
        }
        else
        {
            if (type.equals("DEPOSIT")) {
                Double currentBalance = user.getBalance();
                user.setBalance(currentBalance + amount);
                double cashback = (amount * 20) / 100;
                userRepository.save(user);
                user.setLasttransactionamount(amount);
                tid = makeEntryInTransaction("Credit", user.getMobileNo(), amount, user.getBalance(), "Deposited in account");
            }
        }
        return tid;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {InsufficientBalanceException.class, WalletDoesNotExistException.class, Exception.class})
    public String transfer(String fromMobileNo, String toMobileNo, Double lasttransactionamount) throws WalletDoesNotExistException,
            InsufficientBalanceException, AmountLessThanEqualToZeroException {
        User fromAssociateAccount = userRepository.findByMobileNo(fromMobileNo);
        User toAssociateAccount = userRepository.findByMobileNo(toMobileNo);

        if (fromAssociateAccount == null || toAssociateAccount == null) {
            throw new WalletDoesNotExistException(fromAssociateAccount==null? fromMobileNo: toMobileNo);
        }
        if (lasttransactionamount <= 0)
        {
            throw new AmountLessThanEqualToZeroException();
        }
        Double fromAssociatedAccountBalance = fromAssociateAccount.getBalance();
        Double toAssociatedAccountBalance = toAssociateAccount.getBalance();

        if (fromAssociatedAccountBalance < lasttransactionamount){
            throw new InsufficientBalanceException(fromAssociateAccount.getMobileNo(),lasttransactionamount);
        }

        fromAssociateAccount.setLasttransactionamount(lasttransactionamount);
        fromAssociateAccount.setBalance(fromAssociatedAccountBalance - lasttransactionamount);
        toAssociateAccount.setLasttransactionamount(lasttransactionamount);
        toAssociateAccount.setBalance(toAssociatedAccountBalance + lasttransactionamount);

        userRepository.save(fromAssociateAccount);
        userRepository.save(toAssociateAccount);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(lasttransactionamount)
                .append(" transferred to account with Mobile No : ")
                .append(toMobileNo)
                .append(" from account with Mobile No : ")
                .append(fromMobileNo);
        fromAssociateAccount.setLasttransactionamount(lasttransactionamount);
        makeEntryInTransaction("Credit", toAssociateAccount.getMobileNo(), lasttransactionamount, toAssociateAccount.getBalance(), stringBuilder.toString());
        String tid = makeEntryInTransaction("Debit", fromAssociateAccount.getMobileNo(),lasttransactionamount,fromAssociateAccount.getBalance(), stringBuilder.toString());

        return tid;

    }



}

