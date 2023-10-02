package com.major.backend.services;

import com.major.backend.exceptions.WalletAlreadyExistException;
import com.major.backend.exceptions.WalletDoesNotExistException;
import com.major.backend.models.User;
import com.major.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.random.RandomGenerator;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAllCustomer() {
        return (List<User>)userRepository.findAll();
    }

    public User findUsersByMobileNo(String mobileNo) {
        return (User) userRepository.findByMobileNo(mobileNo);
    }

    public String getAccountName(String mobileNo) throws WalletDoesNotExistException {
        User account = userRepository.findByMobileNo(mobileNo);
        if (account==null) {
            throw new WalletDoesNotExistException(mobileNo);
        }
        return account.getName();
    }

    public String registerUser(User user) throws WalletAlreadyExistException {
            if (userRepository.findByMobileNo(user.getMobileNo()) != null) {
                throw new WalletAlreadyExistException(user.getMobileNo());
            }
            user.setId(RandomGenerator.getDefault().nextInt(999999999));
            user.setMobileNo(user.getMobileNo());
            user.setName(user.getName());
            user.setLasttransactionamount(0.0);
            user.setBalance(0.0);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "User created";
    }

    public String signInUser(User user) {
        if(user.getMobileNo()==null||user.getPassword()==null)
            return "Bad credentials";
        User user1 = userRepository.findByMobileNo(user.getMobileNo());
        if(bCryptPasswordEncoder.matches(user.getPassword() ,user1.getPassword()))
            return "Successful";
        else
            return "Bad Credentials";
    }
}
