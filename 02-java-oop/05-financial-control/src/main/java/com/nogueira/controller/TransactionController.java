package com.nogueira.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.nogueira.enums.Category;
import com.nogueira.dao.TransactionDAO;
import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;
import com.nogueira.service.FinancialReportService;
import com.nogueira.service.StatementService;
import com.nogueira.utils.InputHelper;
import com.nogueira.view.StatementView;

/**
 * Controlador responsável por coordenar a interação entre o usuário (View)
 * e o domínio do sistema (Entities/Services).
 * Gerencia o fluxo de criação, visualização e remoção de transações.
 */
public class TransactionController {
    private final TransactionDAO transactionDAO;
    public TransactionController(TransactionDAO dao) { 
        this.transactionDAO = dao; 
    }

    public void addTransaction(User user, TransactionType type) {
        if (type == TransactionType.INCOME) {
            System.out.println("\n=== ADDING INCOME ===");
        } else {
            System.out.println("\n=== ADDING EXPENSE ===");
        }
        Category cat = readCategory(type);
        String desc = InputHelper.readNonEmptyString("Description: ");
        BigDecimal value = InputHelper.readBigDecimal("Value: ");

        Transaction t = new Transaction(desc, value, type, cat);

        user.addTransaction(t);
        System.out.println("Success!");
    }

    public Category readCategory(TransactionType type) {
        if (type == TransactionType.INCOME) {
            System.out.println("1. SALARY | 2. FREELANCE | 3. DIVIDENDS | 4. SALES | 5. GIFTS | 6.OTHERS");
            int option = InputHelper.readInt("Choose income category: ");
            return switch (option) {
                case 1 -> Category.SALARY;
                case 2 -> Category.FREELANCE;
                case 3 -> Category.DIVIDENDS;
                case 4 -> Category.SALES;
                case 5 -> Category.GIFTS;
                default -> Category.OTHERS;
            };
        } else {
            System.out.println("1. FOOD | 2. TRANSPORT | 3. BILLS | 4. LEISURE | 5. OTHERS");
            int option = InputHelper.readInt("Choose category: ");
            return switch (option) {
                case 1 -> Category.FOOD;
                case 2 -> Category.TRANSPORT;
                case 3 -> Category.BILLS;
                case 4 -> Category.LEISURE;
                default -> Category.OTHERS;
            };
        }
    }

    public List<Transaction> seeStatement(User user) {
        int option = InputHelper.readInt("Show: 1. All | 2. Income | 3. Expense: ");
        TransactionType selectedType = switch (option) {
            case 2 -> TransactionType.INCOME;
            case 3 -> TransactionType.EXPENSE;
            default -> null;
        };

        Category filterCat = null;
        if (selectedType != null) {
            int catOption = InputHelper.readInt("1. All | 2. By Category: ");
            filterCat = switch (catOption) {
                case 2 -> readCategory(selectedType);
                default -> null;
            };
        }
        int periodOption = InputHelper
                .readInt("\nPeriod: 1. All | 2. Today | 3. Last 7 Days | 4. This Month | 5. Custom: ");
        
        LocalDate start;
        LocalDate end = LocalDate.now();        
        switch (periodOption) {
            case 2 -> start = LocalDate.now();
            case 3 -> start = end.minusDays(7);
            case 4 -> start = LocalDate.now().withDayOfMonth(1);
            case 5 -> {
                start = InputHelper.readDate("Start Date (YYYY-MM-DD): ");
                end = InputHelper.readDate("End Date (YYYY-MM-DD): ");
                if (start.isAfter(end)) {

                //(Auto-swap):Garante que o intervalo de datas seja válido
                // mesmo que o usuário inverta a ordem de digitação.
                System.out.println("[!] Start date was after end date. Automatically swapping for consistency.");
                LocalDate temp = start;
                start = end;
                end = temp;
                }
            }
            default -> {
                start = user.getTransactions().isEmpty() ? LocalDate.now() : user.getTransactions().get(0).getDate();
            }
        }
        List<Transaction> filtered = StatementService.filterTransactions(user, selectedType, filterCat, start, end);
        StatementView.printList(filtered);
        return filtered;
}

    public void removeTransaction(User user) {
        List<Transaction> filtered = seeStatement(user);

        if (filtered.isEmpty())
            return;

        int idTarget = InputHelper.readInt("\nEnter the ID to DELETE: ");

        boolean isVisible = filtered.stream().anyMatch(t -> t.getId() == idTarget);   

        if (isVisible) {
            user.removeTransactionById(idTarget);   
            transactionDAO.save(user);
            System.out.println("SUCCESS: Transaction removed!");
        } else {
            System.out.println("ERROR: ID not found!");
        }
    }

    public void seeReport(User user) {
        Map<Category, BigDecimal> report = FinancialReportService.calculateTotals(user.getTransactions());
        int option = InputHelper.readInt("Show: 1. All | 2. Income | 3. Expense: ");
        switch (option) {
            case 2 -> {
                List<Transaction> income = user.getTransactions().stream()
                        .filter(t -> t.getType() == TransactionType.INCOME)
                        .toList();
                Map<Category, BigDecimal> reportIncome = FinancialReportService.calculateTotals(income);
                reportIncome.forEach((cat, total) -> System.out.println("[INCOME] " + cat + ": R$ " + total));
            }
            case 3 -> {
                List<Transaction> expense = user.getTransactions().stream()
                        .filter(t -> t.getType() == TransactionType.EXPENSE)
                        .toList();
                Map<Category, BigDecimal> reportExpense = FinancialReportService.calculateTotals(expense);
                reportExpense.forEach((cat, total) -> System.out.println("[EXPENSE] " + cat + ": R$ " + total));
            }
            default -> report.forEach((cat, total) -> System.out.println(cat + ": R$ " + total));

        }
    }

}
