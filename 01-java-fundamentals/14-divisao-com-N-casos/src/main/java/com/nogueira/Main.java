package com.nogueira;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantas divisões deseja fazer? ");
        int n = sc.nextInt();

        String resultados = "";

        for (int i=0; i<n; i++){
            System.out.printf("Numerador: ");
            double numerador = sc.nextDouble();
            System.out.print("Denominador: ");
            double denominador = sc.nextDouble();

            if (denominador == 0){
                resultados += "divisão impossivel\n";
            }
            else{
                double calculo = numerador / denominador;
                resultados += calculo + "\n";
            }
        }                    
        System.out.println(resultados);
        
        sc.close();
    }

}