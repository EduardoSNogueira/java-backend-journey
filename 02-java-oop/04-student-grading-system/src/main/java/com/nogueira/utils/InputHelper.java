package com.nogueira.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    public static double readValidatedDouble(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = sc.nextDouble();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    public static String readValidatedString(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim(); // .trim() remove espaços vazios

            if (input.isEmpty()) {
                System.out.println("Error: This field cannot be empty. ");
                continue;
            }
            return input;
        }
    }

    public static Double readCappedGrade(Scanner sc, String message, double maxLimit) {
        while (true) {
            double value = readValidatedDouble(sc, message + " (Max: " + maxLimit + "): ");
            if (value >= 0 && value <= maxLimit) {
                return value;
            }
            System.out.println("Error: Grade must be between 0 and " + maxLimit);
        }

    }
}