package com.nogueira.entities;

import java.time.LocalDate;

import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
import com.nogueira.utils.InputHelper;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Representa uma transação financeira individual no WiseCash.
 * Esta classe gerencia sua própria identidade através de um contador estático,
 * garantindo IDs sequenciais durante a execução do sistema.
 */
public class Transaction {
    /** Contador para geração de IDs únicos. Resetado via {@link #setNextId(int)}. */
    private static int nextId = 1;
    private final int id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;
    private Category category;

    /**
     * Construtor para novas transações originadas pela interface do usuário.
     * Valida os dados de entrada e gera automaticamente o ID e a data atual.
     * * @throws IllegalArgumentException se o valor for menor ou igual a zero.
     */    
    public Transaction(String description, BigDecimal amount, TransactionType type, Category category) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive and not null.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;

        this.id = generateId();
        this.date = LocalDate.now();
    }

    /**
     * Construtor de persistência. Utilizado para reconstruir objetos a partir do CSV.
     * Não gera novo ID nem valida dados, assumindo que os dados lidos são íntegros.
     */    
    public Transaction(int id, String description, BigDecimal amount, TransactionType type, Category category,
            LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }
    /**
     * Construtor simplificado para fins de teste e mock de relatórios.
     * Define ID como 0 e tipo fixo como DESPESA.
     */
    public Transaction(Category category, BigDecimal amount) {
        this.id = 0;
        this.category = category;
        this.amount = amount;
        this.description = "Teste";
        this.type = TransactionType.EXPENSE;
    }

    public int getId() {
        return this.id;
    }

    private int generateId() {
        return nextId++;
    }

    public static void setNextId(int lastIdFound) {
        nextId = lastIdFound;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getSignedAmount() {
        if (this.type == TransactionType.EXPENSE) {
            return this.amount.negate();
        } else {
            return this.amount;
        }
    }

    public TransactionType getType() {
        return this.type;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(InputHelper.BRAZIL);

        String valorFormatado = currencyFormater.format(getSignedAmount());

        return String.format("ID: %03d | %2$td/%2$tm/%2$tY | %3$-15s | %4$12s | %5$s",
                getId(),
                getDate(),
                getDescription(),
                valorFormatado,
                getCategory());
    }
}
