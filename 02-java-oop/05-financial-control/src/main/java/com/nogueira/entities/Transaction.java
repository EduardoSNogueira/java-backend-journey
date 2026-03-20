package com.nogueira.entities;

import java.time.LocalDate;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

public class Transaction {

    private String description;
    private Category category;
    private double amount;
    private TransactionType type;
    private LocalDate date;

    public Transaction(String description, Category category, double amount, TransactionType type, LocalDate date) {
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Transaction(String description, Category category, double amount, TransactionType type) {
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.date = LocalDate.now(); // O sistema carimba a data de hoje aqui!
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getAmount() {
        return this.amount;
    }

    public double getSignedAmount() {
        if (this.type == TransactionType.EXPENSE) {
            return this.amount * -1;
        } else {
            return this.amount;
        }
    }

    public TransactionType getType() {
        return this.type;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
