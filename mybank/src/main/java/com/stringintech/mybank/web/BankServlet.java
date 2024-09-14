package com.stringintech.mybank.web;

import com.stringintech.mybank.context.Context;
import com.stringintech.mybank.model.Transaction;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BankServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {

            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String reference = request.getParameter("reference");

            Transaction tx = Context.transactionService.create(amount, reference);

            response.setContentType("application/json; charset=UTF-8");
            String json = Context.objectMapper.writeValueAsString(tx);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Transaction> txs = Context.transactionService.getAll();
            response.getWriter().print(Context.objectMapper.writeValueAsString(txs));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}