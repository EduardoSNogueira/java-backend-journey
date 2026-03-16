package com.nogueira;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("-------Cálculo da Área------");
        System.out.print("Digite o valor do A: ");
        double a  = sc.nextDouble();
        
        System.out.print("Digite o valor do B: ");
        double b  = sc.nextDouble();

        System.out.print("Digite o valor do C: ");
        double c  = sc.nextDouble();

        double triangulo = (a * c) / 2.0 ;
        double circulo = Math.PI * Math.pow(c, 2);
        double trapezio = ( ( b * a) * c ) / 2.0;
        double quadrado = b * b;
        double retangulo = a * b;
        System.out.printf("TRIANGULO: %.3f%n" +
                          "CIRCULO:   %.3f%n" +
                          "TRAPEZIO:  %.3f%n" +
                          "QUADRADO:  %.3f%n" +
                          "RETANGULO: %.3f%n",
                          triangulo, circulo, trapezio, quadrado, retangulo);
        
        
        sc.close();
    }
}