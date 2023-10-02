package com.major.backend.exceptions;

public class WalletAlreadyExistException extends Exception{
    public WalletAlreadyExistException(String email){
        super("Wallet with Email ID: " + email + " already exist");
    }
}
