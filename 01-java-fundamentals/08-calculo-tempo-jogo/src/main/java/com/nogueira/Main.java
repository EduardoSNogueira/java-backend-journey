package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Inicio do jogo: ");
        int hinicio = sc.nextInt();
        System.out.println("Final do jogo: ");
        int hFinal = sc.nextInt();
        
        int tempoDeJogo; 

        if (hinicio < hFinal) {
            tempoDeJogo = hFinal - hinicio;
            System.out.println(tempoDeJogo);
            
        }
        else {
            tempoDeJogo = ((24 - hinicio) + hFinal);
            System.out.println(tempoDeJogo);

        }

        sc.close();
    }
}