package com.nogueira.service;

import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialReportServiceTest {

@Test
@DisplayName("Should correctly group and sum all categories in the report")
void shouldGroupAndSumAllCategoriesInReport() {
    // Arrange
    List<Transaction> transactions = List.of(
        new Transaction(Category.FOOD, new BigDecimal("250.00")),
        new Transaction(Category.FOOD, new BigDecimal("400.00")),
        new Transaction(Category.TRANSPORT, new BigDecimal("100.00"))
    );

    // Act
    Map<Category, BigDecimal> result = FinancialReportService.calculateTotals(transactions);

    // Assert

    assertTrue(new BigDecimal("650.00").compareTo(result.get(Category.FOOD)) == 0);
    assertTrue(new BigDecimal("100.00").compareTo(result.get(Category.TRANSPORT)) == 0);
    assertEquals(2, result.size());
}
}