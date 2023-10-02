package com.major.backend.exceptions;

public class AmountLessThanEqualToZeroException extends Exception{
    public AmountLessThanEqualToZeroException() {
        super("The amount should be greater than zero");
    }
}

