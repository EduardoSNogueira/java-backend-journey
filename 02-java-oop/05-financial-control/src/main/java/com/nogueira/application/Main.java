package com.nogueira.application;

import com.nogueira.controller.UserController;
import com.nogueira.dao.TransactionCSVDAO;
import com.nogueira.entities.User;
import com.nogueira.enums.TransactionType;
import com.nogueira.controller.TransactionController;
import com.nogueira.utils.InputHelper;
import com.nogueira.dao.TransactionDAO;
import com.nogueira.dao.UserCSVDAO;
import com.nogueira.dao.UserDAO;

public class Main {
    public static void main(String[] args) {

        UserDAO userDAO = new UserCSVDAO();
        TransactionDAO csvRepo = new TransactionCSVDAO();

        UserController userController = new UserController(userDAO);
        TransactionController controller = new TransactionController(csvRepo);

        User user = userDAO.load();

        if (user == null) {
            user = userController.createNewUser();
        }

        csvRepo.load(user);

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
                case 1 -> controller.addTransaction(user, TransactionType.INCOME);
                case 2 -> controller.addTransaction(user, TransactionType.EXPENSE);
                case 3 -> controller.seeStatement(user);
                case 4 -> controller.removeTransaction(user);
                case 5 -> controller.seeReport(user);
                case 6 -> {
                    System.out.println("Saving...");
                    csvRepo.save(user);
                    running = false;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}