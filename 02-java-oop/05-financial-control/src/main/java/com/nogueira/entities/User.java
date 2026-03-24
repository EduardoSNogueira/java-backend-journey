package com.nogueira.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;

public class User {
    private List<Transaction> transactions = new ArrayList<>();
    private String name;
    private LocalDate birthDate;
    private BigDecimal initialBalance;

    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public BigDecimal calculateBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction t : transactions) {
            balance = balance.add(t.getSignedAmount());
        }
        return balance;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        LocalDate today = LocalDate.now(); // data de hoje
        long age = ChronoUnit.YEARS.between(this.birthDate, today);// quantos anos entre a data de nascimento e hoje
        return (int) age;// converte para 'int'
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        this.transactions.add(t);
    }

}
