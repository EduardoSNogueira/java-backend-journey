package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner sc  = new Scanner(System.in);

        System.out.println("------- Calculadora de Idade em Dias-------");
        System.out.print("Qual seu nome? ");
        String nome = sc.nextLine();

        System.out.print("Em que ano você nasceu? ");
        int anoDeNascimento = sc.nextInt();

        System.out.print("Em que ano você esta? ");
        int anoAtual = sc.nextInt();
        
        int idadeAproximadaAnos = anoAtual - anoDeNascimento;
        int idadeAproximadaDias = 365 * idadeAproximadaAnos;

        System.out.printf("Olá %s! Você nasceu em %d, logo tem %d anos. Isso dá aproximadamente %d dias de vida!%n", nome, anoDeNascimento, idadeAproximadaAnos, idadeAproximadaDias);
        
        sc.close();
    }
}