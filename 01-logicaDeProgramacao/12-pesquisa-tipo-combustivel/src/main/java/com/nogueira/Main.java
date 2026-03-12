package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.printf("---COMBUSTIVEL ABASTECIDO---%n" +
                          "1-ALCOOL   %n" + 
                          "2-GASOLINA %n" +
                          "3-DIESEL   %n" +
                          "4-SAIDA    %n");
        int x = sc.nextInt();

        int alcool = 0;
        int gasolina = 0;
        int diesel = 0;

        while (x != 4) {

            switch (x) {
                case 1:
                    alcool += 1;
                    break;
                case 2:
                    gasolina += 1;
                    break;
                case 3:
                    diesel += 1;
                    break;
                default:

                    break;
            }
            x = sc.nextInt();
        }
        System.out.printf("MUITO OBRIGADO%n" +
                          "1-ALCOOL:   %d%n" +
                          "2-GASOLINA: %d%n" +
                          "3-DIESEL:   %d%n", 
                          alcool, gasolina, diesel);
        sc.close();
    }

}