package com.major.backend.exception;

import com.major.backend.exceptions.WalletAlreadyExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WalletAlreadyExistExceptionTest {

    @Test
    @DisplayName("Testing for exception message")
    public void testExceptionMessage() {
        String email = "example@gmail.com";
        WalletAlreadyExistException exception = new WalletAlreadyExistException(email);
        String expectedMessage = "Wallet with Email ID: " + email + " already exist";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
