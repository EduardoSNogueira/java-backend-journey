package com.nogueira.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.nogueira.entities.Transaction;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;
import com.nogueira.utils.InputHelper;
import com.nogueira.enums.Category;

public class UserController {

    public static User createNewUser() {
        System.out.println("   WELCOME TO WISECASH - 1st ACCESS   ");
        String name = InputHelper.readNonEmptyString("Enter your full name: ");
        LocalDate birthDate = InputHelper.readDate("Enter your birth date (YYYY-MM-DD): ");

        User user = new User(name, birthDate);

        BigDecimal initialBalance = InputHelper.readBigDecimal("Enter your current account balance: ");
        if (initialBalance.compareTo(BigDecimal.ZERO) > 0) {
            Transaction firstDeposit = new Transaction("Initial Balance", initialBalance, TransactionType.INCOME,
                    Category.INITIAL);
            user.addTransaction(firstDeposit);
        }
        return user;
    }

}