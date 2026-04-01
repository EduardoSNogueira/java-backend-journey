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
@DisplayName("Deve agrupar e somar corretamente todas as categorias no relatório")
void deveGerarRelatorioAgrupadoCorretamente() {
    // Arrange
    List<Transaction> transacoes = List.of(
        new Transaction(Category.FOOD, new BigDecimal("250.00")),
        new Transaction(Category.FOOD, new BigDecimal("400.00")),
        new Transaction(Category.TRANSPORT, new BigDecimal("100.00"))
    );

    // Act
    Map<Category, BigDecimal> resultado = FinancialReportService.calculateTotals(transacoes);

    // Assert
    // Valida FOOD (650.00)
    assertTrue(new BigDecimal("650.00").compareTo(resultado.get(Category.FOOD)) == 0);
    // Valida TRANSPORT (100.00)
    assertTrue(new BigDecimal("100.00").compareTo(resultado.get(Category.TRANSPORT)) == 0);
    // Valida que só existem 2 categorias no mapa
    assertEquals(2, resultado.size());
}
}