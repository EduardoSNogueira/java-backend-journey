package com.nogueira.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;

public class TransactionRepository {
    private static final String PATH = "transactions.csv";

    public static void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
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
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void load(User user) {
        File file = new File(PATH);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader((PATH)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Transaction t = new Transaction(
                        data[1],
                        Category.valueOf(data[4]),
                        Double.parseDouble(data[2]),
                        TransactionType.valueOf(data[3]),
                        LocalDate.parse(data[0]));
                user.addTransaction(t);
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
