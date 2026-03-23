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
                String line = String.format("%d,%s,%s,% .2f,%s,%s",
                        t.getId(),
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

        user.getTransactions().clear();

        try (BufferedReader br = new BufferedReader(new FileReader((PATH)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Transaction t = new Transaction(
                        Integer.parseInt(data[0]),
                        data[2],
                        Double.parseDouble(data[3]),
                        TransactionType.valueOf(data[4]),
                        Category.valueOf(data[5]),
                        LocalDate.parse(data[1]));
                user.addTransaction(t);
            }
            int maxId = 0;
            for (Transaction t : user.getTransactions()) {
                if (t.getId() > maxId) {
                    maxId = t.getId();
                }
            }
            Transaction.setNextId(maxId + 1);

        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());

        }
    }
}
