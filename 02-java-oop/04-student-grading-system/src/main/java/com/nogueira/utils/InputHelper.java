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
}