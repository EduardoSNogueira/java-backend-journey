package com.nogueira;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);        

        System.out.println("-----CALCULO SALARIO-----");
        System.out.print("Qual seu numero interno: ");
        int numeroFuncionario = sc.nextInt();

        System.out.print("Horas trabalhadas: ");
        double horasTrabalhadas = sc.nextDouble();

        System.out.print("Valor da hora trabalhada: ");
        double valorHoraTrabalhada = sc.nextDouble();

        double salario = horasTrabalhadas * valorHoraTrabalhada;
        System.out.printf("NUMBER = %d%nSALARY = $ %.2f%n",numeroFuncionario, salario);

        sc.close();
    }
}