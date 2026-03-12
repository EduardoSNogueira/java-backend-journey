package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Digite uma nota: ");
        int nota = sc.nextInt();
        
        switch (nota) {
            case 0:
                System.out.println("Insuficiente");
                break;
            case 1:
                System.out.println("Regular");
                break;
            case 2:
                System.out.println("Bom");
                break;    
            case 3:
                System.out.println("Muito Bom");
                break;
            case 4:
                System.out.println("Excelente");
                break;
            default:
               System.out.println("Nota inválida");
                break;
        }       
        sc.close();
    }
}