package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um valor: ");
        double valor = sc.nextDouble();
                
        if (valor < 0 || valor > 100) {
            System.out.println("Fora do intervalo");
        } 
        else if (valor <= 25) {
            System.out.println("Intervalo: [0,25]");
        } 
        else if (valor <= 50) {
            System.out.println("Intervalo: (25,50]");
        } 
        else if (valor <= 75) {
            System.out.println("Intervalo: (50,75]");
        } 
        else {
            System.out.println("Intervalo: (75,100]");
        }
        sc.close();
    }
}