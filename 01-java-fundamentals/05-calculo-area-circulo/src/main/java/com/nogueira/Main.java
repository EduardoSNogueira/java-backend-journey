package com.nogueira;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("-------Cálculo da Área do Círculo------");
        System.out.print("Digite o valor do raio: ");
        double raio  = sc.nextDouble();
        
        double area = Math.PI * Math.pow(raio, 2);
        System.out.printf("O valor da área do circulo é: %.4f%n", area);
        
        
        sc.close();
    }
}