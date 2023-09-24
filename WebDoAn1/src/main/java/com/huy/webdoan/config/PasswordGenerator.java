package com.huy.webdoan.config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
@Component
public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }
        return password.toString();
    }

    public static void main(String[] args) {
        String randomPassword = generateRandomPassword(6);
        System.out.println("Random Password: " + randomPassword);
    }
}
