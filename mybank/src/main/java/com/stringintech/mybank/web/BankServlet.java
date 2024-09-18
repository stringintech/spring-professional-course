package com.stringintech.mybank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stringintech.mybank.context.Context;
import com.stringintech.mybank.model.Transaction;
import com.stringintech.mybank.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BankServlet extends HttpServlet {

    private TransactionService transactionService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Context.class);
        this.transactionService = ctx.getBean(TransactionService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {

            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String reference = request.getParameter("reference");

            Transaction tx = this.transactionService.create(amount, reference);

            response.setContentType("application/json; charset=UTF-8");
            String json = this.objectMapper.writeValueAsString(tx);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Transaction> txs = this.transactionService.getAll();
            response.getWriter().print(this.objectMapper.writeValueAsString(txs));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}