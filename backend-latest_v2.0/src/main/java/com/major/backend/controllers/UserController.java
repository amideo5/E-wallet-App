package com.major.backend.controllers;

import com.major.backend.exceptions.WalletAlreadyExistException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.User;
import com.major.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllCustomers() {
        List<User> users = userService.findAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/getUserByMobileNo/{mobileNo}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("mobileNo") String mobileNo) {
        User users = (User) userService.findUsersByMobileNo(mobileNo);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/name/{mobileNo}")
    public ResponseEntity<?> getAccountName(@PathVariable String mobileNo) {
        try {
            String name = userService.getAccountName(mobileNo);
            return ResponseEntity.status(HttpStatus.OK).body(name);
        } catch (WalletDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    private ResponseEntity<?> registerUser(@RequestBody User user) throws WalletAlreadyExistException {
        try {
            String register = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("User created");
        }catch (WalletAlreadyExistException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
        }
    }

    @PostMapping("/signin")
    private ResponseEntity<?> authenticateUser(@RequestBody User user){
        try {
            String signin = userService.signInUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(signin);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}