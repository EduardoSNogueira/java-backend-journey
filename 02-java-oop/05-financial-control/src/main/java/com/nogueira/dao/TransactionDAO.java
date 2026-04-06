package com.nogueira.dao;

import com.nogueira.entities.User;

/**
 * Contrato de persistência para movimentações financeiras.
 */
public interface TransactionDAO {
    void save(User user);
    void load(User user);
}