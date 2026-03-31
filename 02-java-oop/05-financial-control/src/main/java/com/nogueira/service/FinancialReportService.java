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

        for (Transaction t : transactions) {
            totals.merge(t.getCategory(), t.getAmount(), BigDecimal::add);
        }
        return totals;
    }

}
