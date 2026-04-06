package com.nogueira.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.nogueira.entities.User;
/**
 * Responsável pela persistência dos dados cadastrais do usuário.
 * Armazena informações básicas como nome e data de nascimento em um arquivo separado
 * para facilitar a inicialização do perfil no arranque do sistema.
 */
public class UserCSVDAO implements UserDAO {
    private static final String PATH = "user_profile.csv";

    @Override
    public void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            String line = String.format("%s,%s",
                    user.getName(),
                    user.getBirthDate());
            bw.write(line);
        } catch (IOException e) {
            System.out.println("Error saving user profile: " + e.getMessage());
        }
    }
    @Override
    public User load() {
        File file = new File(PATH);
        if (!file.exists())
            return null;

        try (BufferedReader br = new BufferedReader(new FileReader((PATH)))) {
            String line = br.readLine();
            if (line != null) {
                String[] data = line.split(",");
                return new User(data[0], LocalDate.parse(data[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading user profile: " + e.getMessage());
        }
        return null;
    }
}
