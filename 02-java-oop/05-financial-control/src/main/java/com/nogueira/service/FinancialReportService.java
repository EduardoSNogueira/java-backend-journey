package com.nogueira.service;

import com.nogueira.entities.Transaction;
import com.nogueira.enums.Category;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Serviço especializado na geração de relatórios e agregações financeiras.
 * Utiliza Java Streams para processamento eficiente de grandes volumes de transações.
 */
public class FinancialReportService {

    public static Map<Category, BigDecimal> calculateTotals(List<Transaction> transacoes) {
        return transacoes.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
                ));
    }
}

