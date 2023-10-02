package com.major.backend.services;

import com.major.backend.exceptions.WalletAlreadyExistException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.User;

import java.util.List;

public interface UserService {

    public List<User> findAllCustomer();

    public User findUsersByMobileNo(String mobileNo);

    public String getAccountName(String mobileNo) throws WalletDoesNotExistException;

    public String registerUser(User user) throws WalletAlreadyExistException;

    public String signInUser(User user);
}
