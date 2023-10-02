package com.major.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transaction")
@TypeAlias("transaction")
public class Transaction {

    @Id
    private String transactionId;
    private String mobileNo;
    private String type;
    private Date timestamp;
    private Double amount;
    private Double balance;
    private String description;

    public Transaction() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getmobileNo() {
        return mobileNo;
    }

    public void setmobileNo(String emailID) {
        this.mobileNo = emailID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction(String mobileNo, String type, Date timestamp, Double amount, Double balance, String description) {
        this.mobileNo = mobileNo;
        this.type = type;
        this.timestamp = timestamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }
}