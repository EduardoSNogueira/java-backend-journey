package com.nogueira.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
import com.nogueira.repository.TransactionRepository;
import com.nogueira.repository.UserRepository;

public class Main {
    private static Scanner sc = new Scanner(System.in);

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
            System.out.println("4. Save & Exit ");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("-----------------------------");
                    addIncome(user);
                    break;
                case 2:
                    System.out.println("-----------------------------");
                    addExpense(user);
                    break;
                case 3:
                    System.out.println("-----------------------------");
                    seeStatement(user);
                    break;
                case 4:
                    System.out.println("-----------------------------");
                    System.out.println("Saving data...");
                    TransactionRepository.save(user);
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

    public static User createNewUser() {
        System.out.println("--------------------------------------");
        System.out.println("   WELCOME TO WISECASH - 1st ACCESS   ");
        System.out.println("--------------------------------------");

        System.out.print("Enter your full name: ");
        String name = sc.nextLine();

        System.out.print("Enter your birth date (YYYY-MM-DD): ");
        String dateStr = sc.nextLine();
        LocalDate birthDate = LocalDate.parse(dateStr);

        System.out.print("Enter your current account balance: ");
        double initialBalance = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nProfile created successfully!");
        return new User(name, birthDate, initialBalance);
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