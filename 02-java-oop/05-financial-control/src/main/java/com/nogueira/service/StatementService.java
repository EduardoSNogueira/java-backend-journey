package com.nogueira.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.nogueira.enums.Category;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;

/**
 * Serviço responsável pela filtragem e exibição de extratos financeiros.
 * Permite a extração de dados por tipo, categoria e intervalos temporais específicos.
 */
public class StatementService {

    public static List<Transaction> filterTransactions(User user, TransactionType type, Category cat, LocalDate start, LocalDate end) {
        return user.getTransactions().stream()
                .filter(t -> (type == null || t.getType() == type))
                .filter(t -> (cat == null || t.getCategory() == cat))
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

    public static void showStatement(User user, TransactionType type, Category cat, LocalDate start, LocalDate end) {
        List<Transaction> filtered = filterTransactions(user, type, cat, start, end);
        
        System.out.println("\n--- STATEMENT FROM " + start + " TO " + end + " ---");
        
        BigDecimal periodTotal = filtered.stream()
                .map(Transaction::getSignedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        filtered.forEach(System.out::println);
        
        System.out.println("Period Balance: R$ " + periodTotal);
    }

    public static void showToday(User user, TransactionType type, Category cat) {
        LocalDate today = LocalDate.now();
        showStatement(user, type, cat, today, today);
    }

    public static void showLastWeek(User user, TransactionType type, Category cat) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);
        showStatement(user, type, cat, start, end);
    }

    public static void showCurrentMonth(User user, TransactionType type, Category cat) {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();
        showStatement(user, type, cat, start, end);
    }

    public static void showCustom(User user, TransactionType type, Category cat, LocalDate start, LocalDate end) {
        showStatement(user, type, cat, start, end);
    }
}