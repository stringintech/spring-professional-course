package com.stringintech.mybank.service;

import com.stringintech.mybank.model.Transaction;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {

    private final static List<Transaction> txs = new CopyOnWriteArrayList<>();

    public TransactionService() {
    }

    public Transaction create(Integer amount, String reference) {
        Transaction tx = new Transaction(amount, reference, ZonedDateTime.now());
        txs.add(tx);
        return tx;
    }

    public List<Transaction> getAll() {
        return txs;
    }
}
