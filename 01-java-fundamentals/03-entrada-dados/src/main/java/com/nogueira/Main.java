package com.nogueira;

import java.util.Locale;
import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;

public class Main {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
   
        String x, s1, s2, s3;
        char  y;

        System.out.println("Digite uma palavra:");
        x = sc.next();
        //sc.next(); para String -- sc.nextInt(); var int  -- sc.nextDouble(); var double
         System.out.println("Você digitou: " + x);
        
        System.out.println("Digite um caractere:");
        y = sc.next().charAt(0);
        //sc.next().charAt(0); var char
        System.out.println("Você digitou; " + y);
        
        sc.nextLine();
        // Este comando "consome" o Enter que ficou sobrando no buffer
        
        System.out.println("Digite três palavras(uma em cada linda):" );
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        s3 = sc.nextLine();

        System.out.println("DADOS DIGITADOS");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
       
        sc.close();
   
    }
}
