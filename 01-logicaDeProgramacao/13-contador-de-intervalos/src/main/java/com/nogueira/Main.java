package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos numeros deseja testar? ");
        int n = sc.nextInt();
        
        
        int dentroIntervalo = 0;
        int foraIntevalo = 0;

        for (int i=0; i<n; i++){
            System.out.print("Valor a ser testado: ");
            int x = sc.nextInt();
           if (x >= 10 && x <= 20) {
                dentroIntervalo++;
           }
           else{
                foraIntevalo++;
           }
            
        }
        System.out.printf("%d in%n" +
                          "%d out%n", dentroIntervalo, foraIntevalo);

        sc.close();
    }

}