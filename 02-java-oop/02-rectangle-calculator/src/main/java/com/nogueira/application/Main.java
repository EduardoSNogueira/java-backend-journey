package com.nogueira.application;

import com.nogueira.entities.Rectangle;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rectangle width:  ");
        double width = sc.nextDouble();
        System.out.print("Enter rectangle height: ");
        double height = sc.nextDouble();

        Rectangle rectangle = new Rectangle(width, height);
        System.out.print(rectangle);
        sc.close();
    }
}
