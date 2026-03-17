package com.nogueira.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.nogueira.entities.Student;
import com.nogueira.utils.InputHelper;

public class Main {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        char resp;
        do {
            String name = InputHelper.readValidatedString(sc, "Name: ");
            double nota1 = InputHelper.readCappedGrade(sc, "Grade 1: ", 30.0);
            double nota2 = InputHelper.readCappedGrade(sc, "Grade 2: ", 35.0);
            double nota3 = InputHelper.readCappedGrade(sc, "Grade 3: ", 35.0);

            list.add(new Student(name, nota1, nota2, nota3));

            System.out.print("Add another student? (y/n): ");
            resp = sc.next().toLowerCase().charAt(0);
            sc.nextLine();

        } while (resp == 'y');

        System.out.println("\n--- FINAL REPORT ---");
        for (Student s : list) {
            System.out.println("--------------------");
            System.out.println(s);
        }
        sc.close();
    }
}
