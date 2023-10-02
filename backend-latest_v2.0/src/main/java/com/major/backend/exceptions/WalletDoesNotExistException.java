package com.major.backend.exceptions;

public class WalletDoesNotExistException extends Exception{
    public WalletDoesNotExistException(String email){
        super("Wallet with Email ID: " + email + " does not exist");
    }
}
