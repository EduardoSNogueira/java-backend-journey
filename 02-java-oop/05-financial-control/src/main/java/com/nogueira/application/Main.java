package com.nogueira.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        loadFromFile(eduardo);
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
                    System.out.println("Saving data...");
                    saveToFile(eduardo);
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

    public static void saveToFile(User user) {
        String path = "transactions.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Transaction t : user.getTransactions()) {
                String line = String.format("%s,%s,% .2f,%s,%s",
                        t.getDate(),
                        t.getDescription(),
                        t.getAmount(),
                        t.getType(),
                        t.getCategory());
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void loadFromFile(User user) {
        String path = "transactions.csv";

        try (BufferedReader br = new BufferedReader(new FileReader((path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                LocalDate date = LocalDate.parse(data[0]);
                String description = data[1];
                double amount = Double.parseDouble(data[2]);
                TransactionType type = TransactionType.valueOf(data[3]);
                Category category = Category.valueOf(data[4]);

                Transaction t = new Transaction(description, category, amount, type, date);
                user.addTransaction(t);
            }
            System.out.println("History loaded successfully!");
        } catch (IOException e) {
            System.out.println("No previous history found. Starting fresh");

        } catch (Exception e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }
}