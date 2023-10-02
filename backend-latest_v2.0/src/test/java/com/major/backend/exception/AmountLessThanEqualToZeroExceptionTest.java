package com.major.backend.exception;

import com.major.backend.exceptions.AmountLessThanEqualToZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AmountLessThanEqualToZeroExceptionTest {

    @Test
    @DisplayName("Testing for exception message")
    public void testConstructor() {
        AmountLessThanEqualToZeroException ex = new AmountLessThanEqualToZeroException();

        assertEquals("The amount should be greater than zero", ex.getMessage());
    }
}
