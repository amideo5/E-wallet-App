package com.major.backend.exception;

import com.major.backend.exceptions.WalletDoesNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class WalletDoesNotExistExceptionTest {

    @Test
    @DisplayName("Testing for exception message")
    public void testExceptionMessage() {
        String email = "test@test.com";
        WalletDoesNotExistException exception = new WalletDoesNotExistException(email);
        String expectedMessage = "Wallet with Email ID: " + email + " does not exist";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
