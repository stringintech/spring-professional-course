package com.stringintech.mybank.service;

import com.stringintech.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private final static List<Transaction> txs = new CopyOnWriteArrayList<>();
    private final String bankSlogan;
    private final DummyService dummyService;

//    @Autowired
//    private DummyService dummyService; //Q1:0 How field injection happens on private fields? Also when does it happen? "After" transaction service instantiation?

    public TransactionService(@Value("${bank.slogan}") String bankSlogan, DummyService dummyService) {
        this.bankSlogan = bankSlogan;
        this.dummyService = dummyService;
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
