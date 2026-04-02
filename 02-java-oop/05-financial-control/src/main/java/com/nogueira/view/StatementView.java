package com.nogueira.view;

import java.util.List;

import com.nogueira.entities.Transaction;

public class StatementView {
    public static void printList(List<Transaction> transactions){
        if (transactions.isEmpty()){
            System.out.println("\n[!]No records found for this period.");
            return;
        }

        System.out.println("---------------------");
        transactions.forEach(System.out::println); 
        System.out.println("---------------------\n");
    }
}
