package com.stringintech.mybank.service;

import com.stringintech.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private final static List<Transaction> txs = new CopyOnWriteArrayList<>();
    private final String bankSlogan;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public Transaction create(Integer amount, String reference) {
        Transaction tx = new Transaction(amount, reference, ZonedDateTime.now(), bankSlogan);
        txs.add(tx);
        return tx;
    }

    public List<Transaction> getAll() {
        return txs;
    }
}
