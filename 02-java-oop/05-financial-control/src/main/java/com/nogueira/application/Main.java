package com.nogueira.application;

import com.nogueira.controller.UserController;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;
import com.nogueira.controller.TransactionController;
import com.nogueira.repository.TransactionRepository;
import com.nogueira.repository.UserRepository;
import com.nogueira.utils.InputHelper;

public class Main {
    public static void main(String[] args) {

        User user = UserRepository.load();

        if (user == null) {
            user = UserController.createNewUser();
            UserRepository.save(user);
        }
        TransactionRepository.load(user);

        boolean running = true;
        while (running) {
            String menu = """
                    --------------------------------------
                                 WiseCash Menu
                    --------------------------------------
                    Hello %s  ||  %d Years
                    Current Balance: R$ %s
                    --------------------------------------
                    1. Add Income
                    2. Add Expense
                    3. View Statement
                    4. DELETE
                    5. Report
                    6. Save & Exit
                    """.formatted(user.getName(), user.getAge(), user.calculateBalance());
            System.out.print(menu);
            int option = InputHelper.readInt("Choose an option: ");

            switch (option) {
                case 1 -> TransactionController.addTransaction(user, TransactionType.INCOME);
                case 2 -> TransactionController.addTransaction(user, TransactionType.EXPENSE);
                case 3 -> TransactionController.seeStatement(user);
                case 4 -> TransactionController.removeTransaction(user);
                case 5 -> TransactionController.seeReport(user);
                case 6 -> {
                    System.out.println("Saving...");
                    TransactionRepository.save(user);
                    running = false;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}