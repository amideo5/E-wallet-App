package com.major.backend.controller;

import com.major.backend.controllers.UserController;
import com.major.backend.models.User;
import com.major.backend.repositories.UserRepository;
import com.major.backend.services.UserService;
import com.major.backend.services.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Test
    @DisplayName("Testing to get all user names")
    void getAllUsers() throws Exception
    {
        User user1=new User("dolo","9935733559","catchmeifyoucan");
        User user2=new User("dolo","9769724755","catchmeifyoucan");

        List<User> users = List.of(user1,user2);

        when(userService.findAllCustomer()).thenReturn(users);
        List<User> users1 = userService.findAllCustomer();
        assertEquals(users,users1);

    }

    @Test
    void testGetUserByMobileNo() throws Exception {
        User user = new User("dolo","9935733559","catchmeifyoucan");

        when(userService.findUsersByMobileNo("9935733559")).thenReturn(user);
        User users1 = userService.findUsersByMobileNo("9935733559");
        assertEquals(user,users1);
    }

    @Test
    public void testGetAccountName() throws Exception {
        User user = new User("dolo","9935733559","catchmeifyoucan");

        when(userService.getAccountName("9935733559")).thenReturn(user.getName());
        String users1 = userService.getAccountName("9935733559");
        assertEquals(user.getName(),users1);
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User("dolo","9935733559","catchmeifyoucan");

        when(userService.registerUser(user)).thenReturn("User created");
        String users1 = userService.registerUser(user);
        assertEquals(userService.registerUser(user),users1);
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        User user = new User("dolo","9935733559","catchmeifyoucan");

        when(userService.signInUser(user)).thenReturn("Successful");
        String users1 = userService.signInUser(user);
        assertEquals(userService.signInUser(user),users1);
    }


}
