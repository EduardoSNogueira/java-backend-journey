package com.nogueira.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner sc = new Scanner(System.in);

    public static final Locale BRAZIL = new Locale("pt", "BR");

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }

    }

    public static BigDecimal readBigDecimal(String message) {
        while (true) {
            try {
                String input = readString(message).replace(",", ".");
                return new BigDecimal(input);

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }

    }

    public static String readString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static LocalDate readDate(String message) {
        while (true) {
            try {
                String input = readString(message);
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Error: Invalid format! Use AAAA-MM-DD.");
            }
        }
    }

    public static String readNonEmptyString(String prompt) {
        String input = "";
        while (input.trim().isEmpty()) {
            input = readString(prompt);
            if (input.trim().isEmpty()) {
                System.out.println("Erro: This field cannot be left empty. Please try again.");
            }
        }
        return input;
    }
}