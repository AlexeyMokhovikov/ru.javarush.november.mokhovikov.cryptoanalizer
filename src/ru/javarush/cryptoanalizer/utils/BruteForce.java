package ru.javarush.cryptoanalizer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForce {
    public Path fileToHack;
    Scanner scanner = new Scanner(System.in);

    public void fileChoice() {

        System.out.println("Какой файл будем взламывать?");
        fileToHack = Path.of(scanner.nextLine());
        String filePathFrom = fileToHack.toString();

        if (Files.isRegularFile(fileToHack)) {

            fileReader(filePathFrom);

        } else {
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }
    private void fileReader(String cipherFile) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(cipherFile))) {

                String stringChars = fileReader.readLine();
                if (!stringChars.isEmpty()) {
                    bruteForce(stringChars);

                }

        } catch (IOException ex) {
            ex.getMessage();

        }
    }
    private void bruteForce(String chars) {

        try (FileWriter fileWriter = new FileWriter("bruteForceFile.txt", true)) {

            for (int key = 0; key < chars.length(); key++) {
                fileWriter.write("Ключ - " + key + " ");
                for (int i = 0; i < chars.length(); i++) {
                        if (Character.isUpperCase(chars.charAt(i))) {
                            int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                            int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                            if (shiftedIndex < 0) {
                                shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                            }
                            char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                            fileWriter.write(cypherChar);

                        } else if (Character.isLowerCase(chars.charAt(i))) {
                            int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                            int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                            if (shiftedIndex < 0) {
                                shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                            }
                            char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                            fileWriter.write(cypherChar);

                        } else if (!Character.isLetter(chars.charAt(i)) && Cipher.ALPHABET.indexOf(chars.charAt(i)) >= 0) {
                            int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                            int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                            if (shiftedIndex < 0) {
                                shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                            }
                            char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                            fileWriter.write(cypherChar);

                        } else {
                            fileWriter.write(chars.charAt(i));
                        }
                }
                fileWriter.write("\n");
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
