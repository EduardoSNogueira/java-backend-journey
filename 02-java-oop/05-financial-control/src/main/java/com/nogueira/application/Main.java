package com.nogueira.application;

import java.time.LocalDate;
import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

public class Main {
    public static void main(String[] args) {
        User eduardo = new User("Eduardo S. Nogueira", LocalDate.of(1996, 5, 8), 2000.0);
        Transaction pixRecebido = new Transaction("PIX da Vovó", Category.OTHERS, 150.0, TransactionType.INCOME);

        // Criando uma Despesa
        Transaction lanche = new Transaction("Hambúrguer", Category.FOOD, 45.0, TransactionType.EXPENSE);

        // Adicionando as transações ao Eduardo
        eduardo.addTransaction(pixRecebido);
        eduardo.addTransaction(lanche);

        // O Resultado Final no Console
        System.out.println("----------------------------------------");
        System.out.println("Olá, " + eduardo.getName() + "!");
        System.out.println("Idade: " + eduardo.getAge() + " anos.");
        System.out.println("Saldo Inicial: R$ " + 500.0);
        System.out.println("Saldo Atual: R$ " + eduardo.calculateBalance());
        System.out.println("----------------------------------------");
    }
}