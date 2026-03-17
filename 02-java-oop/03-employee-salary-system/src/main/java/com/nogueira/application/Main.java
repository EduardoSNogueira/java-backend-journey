package com.nogueira.application;

import java.util.Locale;
import java.util.Scanner;

import com.nogueira.entities.Employee;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.nextLine();

        double grossSalary = readValidatedDouble(sc, "Gross salary: ");
        double tax = readValidatedDouble(sc, "Tax: ");

        // Criando o objeto original
        Employee employee = new Employee(name, grossSalary, tax);
        System.out.println("\nEmployee" + employee);

        while (true) {
            try {
                System.out.println("Which percentage to increase salary? ");
                double percentage = sc.nextDouble();

                Employee updatedEmployee = employee.applyIncrease(percentage);
                System.out.println("Updated data:  " + updatedEmployee);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                sc.next();
            }
        }
        sc.close();
    }

    public static double readValidatedDouble(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextDouble(); // Retorna o valor se for correto
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                sc.nextLine(); // Limpa o buffer do scanner
            }
        }
    }
}
