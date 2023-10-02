package com.major.backend.repositories;

import com.major.backend.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Long> {

    ArrayList<Transaction> findByMobileNo(String mobileNo);

}
