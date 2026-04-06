package com.nogueira.dao;

import com.nogueira.entities.User;

/**
 * Contrato de persistência para movimentações financeiras.
 */
public interface UserDAO {
    void save(User user);
    User load();
}