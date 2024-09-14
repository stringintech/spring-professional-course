package com.stringintech.mybank.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stringintech.mybank.service.TransactionService;

public class Context {
    public final static TransactionService transactionService = new TransactionService();
    public final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
}
