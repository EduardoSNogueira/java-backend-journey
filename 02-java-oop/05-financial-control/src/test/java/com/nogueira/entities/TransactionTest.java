package com.nogueira.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nogueira.entities.TransactionTest;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
public class TransactionTest {

    @BeforeEach
    void setup(){
        Transaction.setNextId(1);
    }
    @Test
    void shouldReturnPositiveAmountWhenTypeIsIncome() {
        BigDecimal value = new BigDecimal("100.00");
        Transaction income = new Transaction("Freelance", value, TransactionType.INCOME, Category.FREELANCE);
        assertEquals(value, income.getSignedAmount(), "Income should remain positive");
        assertEquals(1, income.getId());
    }
    @Test
    void shouldReturnNegativeAmountWhenTypeIsExpense() {
        BigDecimal value = new BigDecimal("50.00");
        Transaction expense = new Transaction("Jantar", value, TransactionType.EXPENSE, Category.FOOD);
        BigDecimal signedValue = expense.getSignedAmount();

        assertEquals(new BigDecimal("-50.00"), signedValue, "Expense should be negated");    
        assertEquals(1, expense.getId());
    }
    @Test
    void shouldIncrementIdSequentially() {
        Transaction t1 = new Transaction("Primeira", new BigDecimal("10.00"), TransactionType.INCOME, Category.OTHERS);
        Transaction t2 = new Transaction("Segunda", new BigDecimal("20.00"), TransactionType.EXPENSE, Category.FOOD);
        Transaction t3 = new Transaction("Terceira", new BigDecimal("30.00"), TransactionType.INCOME, Category.SALARY);

        assertEquals(1, t1.getId());
        assertEquals(2, t2.getId());
        assertEquals(3, t3.getId());
}
}
