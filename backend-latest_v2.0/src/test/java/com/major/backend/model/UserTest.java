package com.major.backend.model;

import com.major.backend.models.Transaction;
import com.major.backend.models.Transfer;
import com.major.backend.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void testGettersAndSetters() {
        long userId = 123456789;
        String name = "Aryan";
        String mobileNo = "9935733559";
        String password = "abc123";
        Double balance = 100.0;
        Double amount = 20.0;

        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setMobileNo(mobileNo);
        user.setPassword(password);
        user.setBalance(balance);
        user.setLasttransactionamount(amount);

        assertEquals(userId, user.getId());
        assertEquals(name, user.getName());
        assertEquals(mobileNo, user.getMobileNo());
        assertEquals(password, user.getPassword());
        assertEquals(balance, user.getBalance());
        assertEquals(amount, user.getLasttransactionamount());

    }

    @Test
    public void testConstructor() {
        long userId = 123456789;
        String name = "Aryan";
        String mobileNo = "9935733559";

        User user = new User(userId, name, mobileNo);

        assertEquals(userId, user.getId());
        assertEquals(name, user.getName());
        assertEquals(mobileNo, user.getMobileNo());
    }

}
