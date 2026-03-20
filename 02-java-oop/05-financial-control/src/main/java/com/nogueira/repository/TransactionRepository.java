package com.nogueira.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;

public class TransactionRepository {
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
        } catch (IOException e) {
            System.out.println("No previous history found. Starting fresh");

        } catch (Exception e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }
}
