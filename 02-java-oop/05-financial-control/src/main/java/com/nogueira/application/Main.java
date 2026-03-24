package com.nogueira.application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
import com.nogueira.repository.TransactionRepository;
import com.nogueira.repository.UserRepository;
import com.nogueira.utils.InputHelper;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

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
                    addIncome(user);
                    break;
                case 2:
                    addExpense(user);
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
        String name = InputHelper.readString("Enter your full name: ");
        LocalDate birthDate = InputHelper.readDate("Enter your birth date (YYYY-MM-DD): ");

        User user = new User(name, birthDate);

        BigDecimal initialBalance = InputHelper.readBigDecimal("Enter your current account balance: ");
        if (initialBalance.compareTo(BigDecimal.ZERO) > 0) {
            Transaction firstDeposit = new Transaction("Initial Balance", initialBalance, TransactionType.INCOME,
                    Category.OTHERS);
            user.addTransaction(firstDeposit);
        }

        return user;
    }

    public static void addIncome(User user) {
        System.out.println("--- ADDING INCOME ---");
        String desc = InputHelper.readString("Description: ");
        BigDecimal value = InputHelper.readBigDecimal("Value: ");
        Transaction t = new Transaction(desc, value, TransactionType.INCOME, Category.OTHERS);
        user.addTransaction(t);
        System.out.println("Success!");

    }

    public static void addExpense(User user) {
        Locale.setDefault(Locale.US);

        System.out.println("\n=== ADDING EXPENSE ===");
        System.out.println("1. FOOD | 2. TRANSPORT | 3. BILLS | 4. LEISURE | 5. OTHERS");
        int option = InputHelper.readInt("Choose category: ");

        Category cat = switch (option) {
            case 1 -> Category.FOOD;
            case 2 -> Category.TRANSPORT;
            case 3 -> Category.BILLS;
            case 4 -> Category.LEISURE;
            default -> Category.OTHERS;
        };

        String desc = InputHelper.readString("Description: ");
        BigDecimal value = InputHelper.readBigDecimal("Value: ");

        Transaction t = new Transaction(desc, value, TransactionType.EXPENSE, cat);
        user.addTransaction(t);
        System.out.println("Success!");

    }

    public static void seeStatement(User user) {
        List<Transaction> list = user.getTransactions();

        if (list.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("--- YOUR TRANSACTIONS ---");
            for (Transaction t : list) {
                System.out.println(t);
            }
            System.out.println("Final Balance: R$ " + user.calculateBalance());
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
            TransactionRepository.save(user); //
        } else {
            System.out.println("ERROR: ID not found!");
        }
    }
}