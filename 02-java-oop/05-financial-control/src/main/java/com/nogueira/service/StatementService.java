package com.nogueira.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.nogueira.enums.Category;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;

public class StatementService {

    public static List<Transaction> printFiltered(User user, TransactionType type, Category cat, LocalDate start,
            LocalDate end) {
        List<Transaction> filtered = new ArrayList<>();
        System.out.println("\n--- STATEMENT FROM " + start + " TO " + end + " ---");
        BigDecimal periodTotal = BigDecimal.ZERO;

        for (Transaction t : user.getTransactions()) {
            boolean matchesType = (type == null) || (t.getType() == type);
            boolean matchesCategory = (cat == null) || (t.getCategory() == cat);
            boolean matchesDate = !t.getDate().isBefore(start) && !t.getDate().isAfter(end);

            if (matchesDate && matchesType && matchesCategory) {
                System.out.println(t);
                filtered.add(t);
                periodTotal = periodTotal.add(t.getSignedAmount());
            }
        }
        System.out.println("Period Balance: R$ " + periodTotal);
        return filtered;
    }

    public static void showToday(User user, TransactionType type, Category cat) {
        LocalDate today = LocalDate.now();
        printFiltered(user, type, cat, today, today);
    }

    public static void showLastWeek(User user, TransactionType type, Category cat) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);
        printFiltered(user, type, cat, start, end);
    }

    public static void showCurrentMonth(User user, TransactionType type, Category cat) {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();
        printFiltered(user, type, cat, start, end);
    }

    public static void showCustom(User user, TransactionType type, Category cat, LocalDate start, LocalDate end) {
        printFiltered(user, type, cat, start, end);
    }
}