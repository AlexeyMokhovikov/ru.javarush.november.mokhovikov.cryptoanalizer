package ru.javarush.cryptoanalizer.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Cypher {
    private static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public Path originFilePath;

    public void fileChoice() {

        System.out.println("Выбери файл для шифровки");
        Scanner scanner = new Scanner(System.in);
        originFilePath = Path.of(scanner.nextLine());
        String filePathFrom = originFilePath.toString();
        System.out.println("Вееди ключ (целое число)");
        int key = scanner.nextInt();

        if (Files.isRegularFile(originFilePath)) {

            fileReader(filePathFrom, key);

        } else {
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }

    private <key> void fileReader(String filePathFrom, int key) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePathFrom))) {

            while (fileReader.ready()) {
                String stringChars = fileReader.readLine();
                cypher(stringChars, key);
            }

        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    private void cypher(String chars, int key) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("cypherFile.txt", true))) {

                for (int i = 0; i < chars.length(); i++) {
                    if (Character.isLetter(chars.charAt(i))){
                    fileWriter.write((char) ((int) chars.charAt(i) + key));
                }
                    else {
                        fileWriter.write(chars.charAt(i));
                    }
                }
            fileWriter.newLine();

        } catch (IOException ex) {
            ex.getMessage();
        }

    }
}


// (.,””:-!? )
