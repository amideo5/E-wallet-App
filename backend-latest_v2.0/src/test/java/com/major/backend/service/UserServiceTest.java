package com.major.backend.service;

import com.major.backend.exceptions.WalletAlreadyExistException;
import com.major.backend.models.User;
import com.major.backend.repositories.UserRepository;
import com.major.backend.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void getAll() throws Exception
    {
        User user2=new User("dolo","123456789","catchmeifyoucan");
        User user1=new User("dolo","987654321","catchmeifyoucan");
        User user3=new User("dolo","123459876","catchmeifyoucan");
        List<User> users = List.of(user1,user2,user3);

        when(userRepository.findAll()).thenReturn(users);
        List<User> users1 = userService.findAllCustomer();
        //System.out.println(users1);
        //System.out.println(users);
        assertEquals(users,users1);
    }

    @Test
    void getbyMobile() throws Exception
    {
        User user1=new User("dolo","123456789","catchmeifyoucan");
        User user2=new User("dolo","987654321","catchmeifyoucan");
        User user3=new User("dolo","123459876","catchmeifyoucan");
        User users = user1;

        when(userRepository.findByMobileNo("123456789")).thenReturn(users);
        User users1 = userService.findUsersByMobileNo("123456789");
        //System.out.println(users1);
        //System.out.println(users);
        assertEquals(user1,users1);
    }

    @Test
    void getName() throws Exception
    {
        User user1=new User("dolo","123456789","catchmeifyoucan");
        User user2=new User("dolo","987654321","catchmeifyoucan");
        User user3=new User("dolo","123459876","catchmeifyoucan");
        User users = user1;
        String name = users.getName();
        when(userRepository.findByMobileNo("123456789")).thenReturn(users);
        String users1 = userService.getAccountName("123456789");
        //System.out.println(users1);
        //System.out.println(users);
        assertEquals(name,users1);
    }

    @Test
    void registerTest() throws Exception{
        User user1=new User();
        user1.setMobileNo("9935733559");
        user1.setName("Aryan");
        user1.setPassword("12345678");
        when(userRepository.findByMobileNo("9935733559")).thenReturn(null);
        String result = userService.registerUser(user1);
        assertEquals("User created", result);
    }

    @Test
    public void testSignInUserReturnsBadCredentialsIfMobileNoIsNull() {
        // Arrange
        User user = new User();
        user.setPassword("password");

        // Act
        String result = userService.signInUser(user);

        // Assert
        assertEquals("Bad credentials", result);
    }

    @Test
    public void testSignInUserReturnsBadCredentialsIfPasswordIsNull() {
        // Arrange
        User user = new User();
        user.setMobileNo("1234567890");

        // Act
        String result = userService.signInUser(user);

        // Assert
        assertEquals("Bad credentials", result);
    }

    @Test
    public void testSignInUserReturnsSuccessfulIfUserExistsAndPasswordMatches() {
        // Arrange
        User user = new User();
        user.setMobileNo("1234567890");
        user.setPassword("password");

        User existingUser = new User();
        existingUser.setMobileNo(user.getMobileNo());
        existingUser.setPassword("encoded_password");

        when(userRepository.findByMobileNo(existingUser.getMobileNo())).thenReturn(existingUser);
        when(bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())).thenReturn(true);

        // Act
        String result = userService.signInUser(user);

        // Assert
        assertEquals("Successful", result);
    }

    @Test
    public void testSignInUserReturnsBadCredentialsIfUserExistsAndPasswordDoesNotMatch() {
        // Arrange
        User user = new User();
        user.setMobileNo("1234567890");
        user.setPassword("password");

        User existingUser = new User();
        existingUser.setMobileNo(user.getMobileNo());
        existingUser.setPassword("encoded_password");

        when(userRepository.findByMobileNo(existingUser.getMobileNo())).thenReturn(existingUser);
        when(bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())).thenReturn(false);

        // Act
        String result = userService.signInUser(user);

        // Assert
        assertEquals("Bad Credentials", result);
    }


}
