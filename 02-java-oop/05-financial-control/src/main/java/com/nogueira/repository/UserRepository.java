package com.nogueira.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.nogueira.entities.User;

public class UserRepository {
    private static final String PATH = "user_profile.csv";

    public static void save(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            String line = String.format("%s,%s,%.2f,",
                    user.getName(),
                    user.getBirthDate(),
                    user.getInitialBalance());
            bw.write(line);
        } catch (IOException e) {
            System.out.println("Error saving user profile: " + e.getMessage());
        }
    }

    public static User load() {
        File file = new File(PATH);
        if (!file.exists())
            return null;

        try (BufferedReader br = new BufferedReader(new FileReader((PATH)))) {
            String line = br.readLine();
            while (line != null) {
                String[] data = line.split(",");
                return new User(data[0], LocalDate.parse(data[1]), Double.parseDouble(data[2]));
            }
        } catch (IOException e) {
            System.out.println("Error loading user profile: " + e.getMessage());
        }
        return null;
    }

}
