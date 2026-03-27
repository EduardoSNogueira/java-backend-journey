package com.nogueira.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
import com.nogueira.repository.TransactionRepository;
import com.nogueira.repository.UserRepository;
import com.nogueira.service.StatementService;
import com.nogueira.utils.InputHelper;

public class Main {
    public static void main(String[] args) {

        User user = UserRepository.load();

        if (user == null) {
            user = createNewUser();
            UserRepository.save(user);
        }
        TransactionRepository.load(user);

        boolean running = true;
        while (running) {
            System.out.println("\n--------------------------------------");
            System.out.println("             WiseCash Menu            ");
            System.out.println("--------------------------------------");
            System.out.println("Hello " + user.getName() + "  ||  " + user.getAge() + " Years");
            System.out.println("Current Balance: R$ " + user.calculateBalance());
            System.out.println("--------------------------------------");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Statement ");
            System.out.println("4. DELETE ");
            System.out.println("5. Save & Exit ");
            int option = InputHelper.readInt("Choose an option: ");

            switch (option) {
                case 1:
                    addTransaction(user, TransactionType.INCOME);
                    break;
                case 2:
                    addTransaction(user, TransactionType.EXPENSE);
                    break;
                case 3:
                    seeStatement(user);
                    break;
                case 4:
                    removeTransaction(user);
                    break;
                case 5:
                    System.out.println("Saving data...");
                    TransactionRepository.save(user);
                    System.out.println("Exiting... See you later!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    public static User createNewUser() {
        System.out.println("   WELCOME TO WISECASH - 1st ACCESS   ");
        String name = InputHelper.readNonEmptyString("Enter your full name: ");
        LocalDate birthDate = InputHelper.readDate("Enter your birth date (YYYY-MM-DD): ");

        User user = new User(name, birthDate);

        BigDecimal initialBalance = InputHelper.readBigDecimal("Enter your current account balance: ");
        if (initialBalance.compareTo(BigDecimal.ZERO) > 0) {
            Transaction firstDeposit = new Transaction("Initial Balance", initialBalance, TransactionType.INCOME,
                    Category.INITIAL);
            user.addTransaction(firstDeposit);
        }
        return user;
    }

    public static Category readCategory(TransactionType type) {
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

    public static void addTransaction(User user, TransactionType type) {
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

    public static void seeStatement(User user) {
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
        switch (periodOption) {
            case 2 -> StatementService.showToday(user, selectedType, filterCat);
            case 3 -> StatementService.showLastWeek(user, selectedType, filterCat);
            case 4 -> StatementService.showCurrentMonth(user, selectedType, filterCat);
            case 5 -> {
                LocalDate start = InputHelper.readDate("Start Date (YYYY-MM-DD): ");
                LocalDate end = InputHelper.readDate("End Date (YYYY-MM-DD): ");
                StatementService.showCustom(user, selectedType, filterCat, start, end);
            }
            default -> {
                LocalDate start = user.getTransactions().isEmpty()
                        ? LocalDate.now()
                        : user.getTransactions().get(0).getDate();
                LocalDate end = LocalDate.now();
                StatementService.showCustom(user, selectedType, filterCat, start, end);
            }
        }

    }

    public static void removeTransaction(User user) {
        seeStatement(user);
        if (user.getTransactions().isEmpty())
            return;

        int idTarget = InputHelper.readInt("\nEnter the ID to DELETE: ");
        boolean removed = user.getTransactions().removeIf(t -> t.getId() == idTarget);

        if (removed) {
            System.out.println("SUCCESS: Transaction removed!");
            TransactionRepository.save(user);
        } else {
            System.out.println("ERROR: ID not found!");
        }
    }
}