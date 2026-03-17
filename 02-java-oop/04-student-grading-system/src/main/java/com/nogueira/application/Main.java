package com.nogueira.application;

import java.util.Locale;
import java.util.Scanner;

import com.nogueira.entities.Student;
import com.nogueira.utils.InputHelper;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String name = InputHelper.readValidatedString(sc, "Name: ");
        double nota1 = InputHelper.readValidatedDouble(sc, "Grade 1: ");
        double nota2 = InputHelper.readValidatedDouble(sc, "Grade 2: ");
        double nota3 = InputHelper.readValidatedDouble(sc, "Grade 3: ");

        Student student = new Student(name, nota1, nota2, nota3);

        System.out.println(student);
        sc.close();
    }
}
