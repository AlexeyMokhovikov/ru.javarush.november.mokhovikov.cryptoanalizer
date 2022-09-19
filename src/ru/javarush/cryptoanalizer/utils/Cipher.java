package ru.javarush.cryptoanalizer.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Cipher {

    static String alphabetUp = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ(.,”:-!? )";
    static String alphabetLow = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static int key;
    private Path originFilePath;
    Scanner scanner = new Scanner(System.in);

    public void fileChoice() {

        System.out.println("Выбери файл для шифровки");
        originFilePath = Path.of(scanner.nextLine());
        String filePathFrom = originFilePath.toString();

        if (Files.isRegularFile(originFilePath)) {

                keyChoice(filePathFrom);


        } else {
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }

    public void keyChoice(String filePathFrom) {
        while (true) {
            try {
                System.out.println("Введи ключ (целое число)");
                key = Integer.parseInt(scanner.nextLine());
                if (key == (int) key && key >= 0) {

                    fileRead(filePathFrom, key);
                    break;

                } else {
                    System.out.println("Ключ должен быть больше нуля! Попробуй снова. Я верю, что ты сможешь!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Ай-ай-ай! Нужно ввести целое число!");
            }
        }
    }

    private void fileRead(String filePathFrom, int key) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePathFrom))) {

            while (fileReader.ready()) {
                String stringChars = fileReader.readLine();
                encrypt(stringChars, key);
            }

        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    private void encrypt(String chars, int key) {

            try (FileWriter fileWriter = new FileWriter("ciKpherFile.txt", true)) {
                for (int i = 0; i < chars.length(); i++) {
                    if (Character.isUpperCase(chars.charAt(i))){
                        int charIndex = alphabetUp.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % alphabetUp.length();
                        char cypherChar = alphabetUp.charAt(shiftedIndex);
                        fileWriter.write(cypherChar);

                    } else if (Character.isLowerCase(chars.charAt(i))) {
                        int charIndex = alphabetLow.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % alphabetLow.length();
                        char cypherChar = alphabetLow.charAt(shiftedIndex);
                        fileWriter.write(cypherChar);
                    
                    } else if (!Character.isLetter(chars.charAt(i)) && alphabetUp.indexOf(chars.charAt(i)) >= 0) {
                        int charIndex = alphabetUp.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % alphabetUp.length();
                        char cypherChar = alphabetUp.charAt(shiftedIndex);
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