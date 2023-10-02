package com.major.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@TypeAlias("user")
public class User {

    @Id
    private long id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;
    private String mobileNo;
    private String password;
    private Double balance;
    private Double lasttransactionamount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getLasttransactionamount() {
        return lasttransactionamount;
    }

    public void setLasttransactionamount(Double lasttransactionamount) {
        this.lasttransactionamount = lasttransactionamount;
    }

    public User(){

    }

    public User(String name, String mobileNo, String password, Double balance, Double amount) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.password = password;
        this.balance = balance;
        this.lasttransactionamount = amount;
    }

    public User(String name, String mobileNo, String password) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.password = password;
    }



    public User(long id, String name, String mobileNo) {
        super();
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
    }
}