package com.nogueira;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("CODIGO DO PRODUTO: ");
        int codigoProduto = sc.nextInt();
        System.out.print("QUANTIDADE: ");
        int quantidade = sc.nextInt();
        
        double valorFinal = 0.00;
        
        if (codigoProduto == 1) {
            valorFinal = 4.00 * quantidade;
        }
        else  if (codigoProduto == 2) {
            valorFinal = 4.50 * quantidade;
        }
        else if (codigoProduto == 3) {
            valorFinal = 5.00 * quantidade;
        }
        else if (codigoProduto == 4) {
            valorFinal = 2.00 * quantidade;
        }
        else if (codigoProduto == 5) {
            valorFinal = 1.50 * quantidade;

        }
        else{
            System.out.println("CODIGO INEXISTENTE");
        }    

        System.out.printf("TOTAL: %.2f%n", valorFinal);

        sc.close();
    }
}