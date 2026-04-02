package com.nogueira.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

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

    public User(String name) {
    this(name, LocalDate.now());
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

    public BigDecimal getTotalByEXPENSE(Category category) {
        BigDecimal total = BigDecimal.ZERO;

        for (Transaction t : transactions) {
            if (t.getCategory() == category && t.getType() == TransactionType.EXPENSE) {
                total = total.add(t.getAmount());
            }
        }
        return total.abs();
    }

    public BigDecimal getTotalByINCOME(Category category) {
        BigDecimal total = BigDecimal.ZERO;

        for (Transaction t : transactions) {
            if (t.getCategory() == category && t.getType() == TransactionType.INCOME) {
                total = total.add(t.getAmount());
            }
        }
        return total.abs();
    }

    public boolean removeTransactionById(int id) {
        return this.transactions.removeIf(t -> t.getId() == id);
    }

}