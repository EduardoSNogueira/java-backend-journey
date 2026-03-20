package com.nogueira.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.nogueira.enums.TransactionType;

import java.util.ArrayList;

public class User {
    private List<Transaction> transactions = new ArrayList<>();
    private String name;
    private LocalDate birthDate;
    private double initialBalance;

    public User(String name, LocalDate birthDate, double initialBalance) {
        this.name = name;
        this.birthDate = birthDate;
        this.initialBalance = initialBalance;
    }

    public double calculateBalance() {
        double balance = initialBalance;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INCOME) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getInitialBalance() {
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
