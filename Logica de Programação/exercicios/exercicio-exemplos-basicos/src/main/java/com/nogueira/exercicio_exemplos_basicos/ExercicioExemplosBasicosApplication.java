package com.nogueira.exercicio_exemplos_basicos;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExercicioExemplosBasicosApplication {

	public static void main(String[] args) {
		
		// --- Declaração de Variáveis ---
		String product1 = "Computer";
		String product2 = "Office desk";

		int age = 30;
		int code = 5290;
		char gender = 'F';

		double price1 = 2100.0;
		double price2 = 650.50;
		double measure = 53.234567;

		// --- Saída de Dados ---
		System.out.printf("Products%n%s, which price is $ %.2f%n", product1, price1);
		System.out.printf("%s, which price is $ %.2f%n", product2, price2);

		// %d para inteiros, %c para char (unico caractere)
		System.out.printf("%nRecord: %d years old, code %d and gender: %c%n", age, code, gender);
		
		// %.8f força 8 casas decimais e preenche com zeros se necessário
		System.out.printf("%nMeasure with eigth decimal places: %.8f%n", measure);
		System.out.printf("Rouded (three decimal places): %.3f%n", measure);
		
		// Configura o padrão americano para as próximas impressões
		Locale.setDefault(Locale.US);
		System.out.printf("US decimal point: %.3f%n", measure);

	}

}
