package com.nogueira.dao;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import com.nogueira.entities.User;
import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
/**
 * Gerencia a persistência de transações em arquivos planos (CSV).
 * <p>
 * O formato do arquivo segue o padrão:
 * ID, Data (ISO), Descrição, Valor (US Locale), Tipo, Categoria
 * </p>
 */
public class TransactionCSVDAO implements TransactionDAO {
    
    private static final String PATH = "transactions.csv";
    /**
     * Salva todas as transações do usuário no arquivo CSV.
     * Utiliza {@link Locale#US} para garantir que o separador decimal seja o ponto (.),
     * evitando conflitos com o separador de colunas (vírgula).
     * * @param user O usuário cujas transações serão persistidas.
     */
    
    @Override
    public void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            for (Transaction t : user.getTransactions()) {
                String line = String.format(Locale.US, "%d,%s,%s,%.2f,%s,%s",
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
    
    @Override
    public void load(User user) {
        File file = new File(PATH);

        if (!file.exists())
            return;

        user.getTransactions().clear();
        int maxIdFound = 0;

        try (BufferedReader br = new BufferedReader(new FileReader((PATH)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int currentId = Integer.parseInt(data[0]);

                if (currentId > maxIdFound) {
                maxIdFound = currentId;
                }   
                
                Transaction t = new Transaction(
                    currentId,
                    data[2],
                    new BigDecimal(data[3].trim()),
                    TransactionType.valueOf(data[4]),
                    Category.valueOf(data[5]),
                    LocalDate.parse(data[1]));
                    user.addTransaction(t);
            }
        Transaction.setNextId(maxIdFound + 1);

        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
