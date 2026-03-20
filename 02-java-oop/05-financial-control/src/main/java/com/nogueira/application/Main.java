package com.nogueira.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        User eduardo = new User("Eduardo S. Nogueira", LocalDate.of(1996, 5, 8), 2000.0);

        boolean running = true;

        while (running) {
            System.out.println("\n=== WiseCash Menu ===");
            System.out.println("Current Balance: R$ " + eduardo.calculateBalance());
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Statement ");
            System.out.println("4. Save & Exit ");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("-----------------------------");
                    addIncome(eduardo);
                    break;
                case 2:
                    System.out.println("-----------------------------");
                    addExpense(eduardo);
                    break;
                case 3:
                    System.out.println("-----------------------------");
                    seeStatement(eduardo);
                    break;
                case 4:
                    System.out.println("-----------------------------");
                    System.out.println("Exiting... See you later!");
                    running = false;
                    break;
                default:
                    System.out.println("-----------------------------");
                    System.out.println("Invalid option! Try again.");
            }

        }
        sc.close();
    }

    public static void addIncome(User user) {
        Locale.setDefault(Locale.US);

        System.out.println("--- ADDING INCOME ---");
        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Value: ");
        double value = sc.nextDouble();
        sc.nextLine();

        Transaction t = new Transaction(desc, Category.OTHERS, value, TransactionType.INCOME);
        user.addTransaction(t);

        System.out.println("Success!");

    }

    public static void addExpense(User user) {
        Locale.setDefault(Locale.US);

        System.out.println("\n=== ADDING EXPENSE ===");
        System.out.println("1. FOOD");
        System.out.println("2. TRANSPORT");
        System.out.println("3. BILLS ");
        System.out.println("4. LEISURE ");
        System.out.println("5. OTHERS ");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        Category categoryType = Category.OTHERS;
        switch (option) {
            case 1:
                categoryType = Category.FOOD;
                break;
            case 2:
                categoryType = Category.TRANSPORT;
                break;
            case 3:
                categoryType = Category.BILLS;
                break;
            case 4:
                categoryType = Category.LEISURE;
                break;
            case 5:
                categoryType = Category.OTHERS;
                break;
            default:
                System.out.println("Invalid option! Try again.");
        }

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Value: ");
        double value = sc.nextDouble();
        sc.nextLine();

        Transaction t = new Transaction(desc, categoryType, value, TransactionType.EXPENSE);
        user.addTransaction(t);

        System.out.println("Success!");

    }

    public static void seeStatement(User user) {
        List<Transaction> list = user.getTransactions();

        if (list.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Transaction t : user.getTransactions()) {
                System.out.println(t);
            }
        }
        System.out.println("-----------------------------");
        System.out.println("Final Balance: R$ " + user.calculateBalance());
    }
}