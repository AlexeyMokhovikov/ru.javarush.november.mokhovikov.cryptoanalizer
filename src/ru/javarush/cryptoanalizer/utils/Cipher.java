package ru.javarush.cryptoanalizer.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Cipher {
    static final String ALPHABET = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя(.,”:-!? )";
    static int key;
    private Path originFilePath;
    Scanner scanner = new Scanner(System.in);

    public void chooseFile() {

        System.out.println("Выбери файл для шифровки");
        originFilePath = Path.of(scanner.nextLine());
        String filePathFrom = originFilePath.toString();

        if (Files.isRegularFile(originFilePath)) {

                chooseKey(filePathFrom);

        } else {
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }

    public void chooseKey(String filePathFrom) {
        while (true) {
            try {
                System.out.println("Введи ключ (целое число)");
                key = Integer.parseInt(scanner.nextLine());
                if (key == (int) key && key >= 0) {

                    readFile(filePathFrom, key);
                    break;

                } else {
                    System.out.println("Ключ должен быть больше нуля! Попробуй снова. Я верю, что ты сможешь!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Ай-ай-ай! Нужно ввести целое число!");
            }
        }
    }

    private void readFile(String filePathFrom, int key) {
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

            try (FileWriter fileWriter = new FileWriter("cipherFile.txt", true)) {
                for (int i = 0; i < chars.length(); i++) {
                    if (Character.isUpperCase(chars.charAt(i))){
                        int charIndex = ALPHABET.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % ALPHABET.length();
                        char cypherChar = ALPHABET.charAt(shiftedIndex);
                        fileWriter.write(cypherChar);

                    } else if (Character.isLowerCase(chars.charAt(i))) {
                        int charIndex = ALPHABET.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % ALPHABET.length();
                        char cypherChar = ALPHABET.charAt(shiftedIndex);
                        fileWriter.write(cypherChar);
                    
                    } else if (!Character.isLetter(chars.charAt(i)) && ALPHABET.indexOf(chars.charAt(i)) >= 0) {
                        int charIndex = ALPHABET.indexOf(chars.charAt(i));
                        int shiftedIndex = (charIndex + key) % ALPHABET.length();
                        char cypherChar = ALPHABET.charAt(shiftedIndex);
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
