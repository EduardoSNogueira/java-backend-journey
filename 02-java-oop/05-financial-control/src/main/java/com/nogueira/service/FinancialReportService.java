package com.nogueira.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nogueira.enums.Category;
import com.nogueira.entities.Transaction;
import java.math.BigDecimal;

public class FinancialReportService {
    public static Map<Category, BigDecimal> calculateTotals(List<Transaction> transactions) {
        Map<Category, BigDecimal> totals = new HashMap<>();

        // Pega (ou Zero) -> Soma -> Substitui
        for (Transaction t : transactions) {
            Category cat = t.getCategory();
            BigDecimal amount = t.getAmount();
            BigDecimal currentTotal = totals.getOrDefault(cat, BigDecimal.ZERO);
            totals.put(cat, currentTotal.add(amount));
        }
        return totals;
    }

}
