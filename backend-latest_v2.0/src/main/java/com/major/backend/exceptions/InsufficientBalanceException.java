package com.major.backend.exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String mobileNo, Double amount)
    {
        super("Insufficient Balance in Wallet associated with Mobile No - "+ mobileNo+ " by amount of - "+ amount );
    }
}