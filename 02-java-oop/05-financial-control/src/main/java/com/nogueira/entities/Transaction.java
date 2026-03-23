package com.nogueira.entities;

import java.time.LocalDate;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

public class Transaction {

    private static int nextId = 1;
    private final int id;
    private String description;
    private double amount;
    private LocalDate date;
    private TransactionType type;
    private Category category;

    // CONSTRUTOR DE NOVAS TRANSAÇOES NO MENU
    public Transaction(String description, double amount, TransactionType type, Category category) {
        this.id = nextId++;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = LocalDate.now();
    }

    // CONTRUTOR PARA CARREGAR TRANSAÇOES DO ARQUIVO
    public Transaction(int id, String description, double amount, TransactionType type, Category category,
            LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public static void setNextId(int lastIdFound) {
        nextId = lastIdFound;
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

    // LOGICA DE NEGOCIO, CALCULA SALDO, ACRESCENTA OU SUBTRAI
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

    @Override
    public String toString() {
        return String.format("ID: %03d | %2$td/%2$tm/%2$tY | %3$-15s | %4$8.2f | %5$s",
                getId(),
                getDate(),
                getDescription(),
                getSignedAmount(),
                getCategory());

    }
}
