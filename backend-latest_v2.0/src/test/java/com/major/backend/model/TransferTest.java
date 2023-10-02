package com.major.backend.model;

import com.major.backend.models.Transfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransferTest {

    @Test
    public void testGettersAndSetters() {
        Transfer transfer = new Transfer("1234567890", "0987654321", 100.0);
        assertEquals("1234567890", transfer.getFromMobileNo());
        assertEquals("0987654321", transfer.getToMobileNo());
        assertEquals(100.0f, transfer.getLasttransactionamount(), 0.0);

        transfer.setFromMobileNo("1111111111");
        assertEquals("1111111111", transfer.getFromMobileNo());

        transfer.setToMobileNo("2222222222");
        assertEquals("2222222222", transfer.getToMobileNo());

        transfer.setLasttransactionamount(50.0);
        assertEquals(50.0f, transfer.getLasttransactionamount(), 0.0);
    }
}
