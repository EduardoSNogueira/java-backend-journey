package com.nogueira.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;

public class User {
    private List<Transaction> transactions;
    private String name;
    private LocalDate birthDate;
    private double initialBalance;

    public User(String name, LocalDate birthDate, double initialBalance) {
        this.name = name;
        this.birthDate = birthDate;
        this.initialBalance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public double calculateBalance() {
        double balance = this.initialBalance;
        for (Transaction t : this.transactions) {
            balance += t.getSignedAmount();
        }
        return balance;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        LocalDate today = LocalDate.now(); // data de hoje
        long age = ChronoUnit.YEARS.between(this.birthDate, today);// quantos anos entre a data de nascimento e hoje
        return (int) age;// converte para 'int'
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

}
