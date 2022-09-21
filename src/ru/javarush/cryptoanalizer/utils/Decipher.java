package ru.javarush.cryptoanalizer.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decipher {
    public Path decipherFilePath;
    Scanner scanner = new Scanner(System.in);

    public void fileChoice() {

        System.out.println("Выбери файл для дешифровки");
        decipherFilePath = Path.of(scanner.nextLine());
        String filePathFrom = decipherFilePath.toString();

        if (Files.isRegularFile(decipherFilePath)) {

            keyChoice(filePathFrom);

        } else {
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }
    private void keyChoice(String filePathFrom) {

        while (true) {
            try {
                System.out.println("Введи ключ (целое число)");
                int key = Integer.parseInt(scanner.nextLine());
                if (key == (int) key && key == Cipher.key && key >= 0) {

                    fileReader(filePathFrom, key);
                    break;

                } else System.out.println("Ключ не подходит! Попробуй снова. У тебя осталось две попытки!\uD83D\uDE00");

            } catch (NumberFormatException nfe) {
                System.out.println("Ай-ай-ай! Нужно ввести целое число!");
            }
        }
    }
    private void fileReader(String filePathFrom, int key) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePathFrom))) {

            while (fileReader.ready()) {
                String stringChars = fileReader.readLine();
                decrypt(stringChars, key);
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    private void decrypt(String chars, int key) {

        try (FileWriter fileWriter = new FileWriter("decypherFile.txt", true)) {

            for (int i = 0; i < chars.length(); i++) {
                if (Character.isUpperCase(chars.charAt(i))){
                    int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                    int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                    if (shiftedIndex < 0){
                        shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                    }
                    char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                    fileWriter.write(cypherChar);

                } else if (Character.isLowerCase(chars.charAt(i))) {
                    int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                    int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                    if (shiftedIndex < 0){
                        shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                    }
                    char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                    fileWriter.write(cypherChar);

                } else if (!Character.isLetter(chars.charAt(i)) && Cipher.ALPHABET.indexOf(chars.charAt(i)) >= 0) {
                    int charIndex = Cipher.ALPHABET.indexOf(chars.charAt(i));
                    int shiftedIndex = (charIndex - key) % Cipher.ALPHABET.length();
                    if (shiftedIndex < 0){
                        shiftedIndex = Cipher.ALPHABET.length() + shiftedIndex;
                    }
                    char cypherChar = Cipher.ALPHABET.charAt(shiftedIndex);
                    fileWriter.write(cypherChar);

                }else {
                    fileWriter.write(chars.charAt(i));
                }
            }
            fileWriter.write("\n");

        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
