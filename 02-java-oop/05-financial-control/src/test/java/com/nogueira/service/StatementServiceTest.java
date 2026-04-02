package com.nogueira.service;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.Category;
import com.nogueira.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class StatementServiceTest {
    private User user;
    private LocalDate today; 

    @BeforeEach
    void setup() {
    user = new User("Eduardo Test", LocalDate.now().minusYears(29));
    today = LocalDate.now();

    user.addTransaction(new Transaction(1, "Salary", new BigDecimal(5000),
                     TransactionType.INCOME, Category.SALARY, today));
    user.addTransaction(new Transaction(2, "Ifood", new BigDecimal(50),
                     TransactionType.EXPENSE, Category.FOOD, today.minusDays(5)));
    user.addTransaction(new Transaction(3, "Cinema", new BigDecimal(30),
                     TransactionType.EXPENSE, Category.LEISURE, today.minusDays(30)));    
    }

    @Test
    void shouldFilterTransactionsFromLastWeek(){
        List<Transaction> result = StatementService.filterTransactions(user, null, null, 
            today.minusDays(7), today);

        assertEquals(2, result.size(),"Should return exactly 2 transactions from the last week.");
    }

    @Test
    void shouldReturnEmptyWhenNoTransactionsInPeriod(){
        List<Transaction> result = StatementService.filterTransactions(user, null, null, 
            today.plusDays(1), today.plusMonths(1));

        assertTrue(result.isEmpty(), "The list should be empty for a future date range with no transactions");
    }


}