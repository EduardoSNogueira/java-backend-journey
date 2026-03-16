package com.nogueira.estudo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudoApplication {

	public static void main(String[] args) {

		int y = 32;
		double x = 10.35784;
		String nome = "Maria";
		int idade = 31;
		double renda = 4000.0;
		
		System.out.println(y);
		System.out.println(x);
		Locale.setDefault(Locale.US);
		//USAR COMANDO PARA CONFIGURAÇOES DE OUTROS PAISES
		System.out.printf("%.2f%n",x);
		System.out.printf("%.4f%n",x);
		//printf PARA DELIMITAR CASA DECIMAL
		System.out.println("Resultado = " + x + " Metros");
		//PARA JUNTAR(concatenar) ultilizar + antes e depois
		System.out.printf("Resultado = %.2f Metros%n", x);
		//PARA CONCATENAR COM DELIMITADOR 
		System.out.printf("%s tem %d anos e ganha R$ %.2f reais%n", nome, idade, renda);
		// %f = PONTO FLUTUANTE %d = INTEIRO %s = texto %n = quebra de linha

	}

}
