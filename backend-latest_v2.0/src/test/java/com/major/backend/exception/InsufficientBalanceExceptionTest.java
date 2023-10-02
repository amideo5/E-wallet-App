package com.major.backend.exception;

import com.major.backend.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InsufficientBalanceExceptionTest {

    @Test
    @DisplayName("Testing for exception message")
    public void testConstructor() {
        String mobileNo = "9876543210";
        Double amount = 100.0;
        InsufficientBalanceException ex = new InsufficientBalanceException(mobileNo, amount);

        assertEquals("Insufficient Balance in Wallet associated with Mobile No - " + mobileNo + " by amount of - " + amount, ex.getMessage());
    }
}
