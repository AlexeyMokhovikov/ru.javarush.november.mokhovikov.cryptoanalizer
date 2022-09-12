package ru.javarush.cryptoanalizer.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cypher {
    public Path originFilePath;
    public Path cypherFilePath;

    public void fileChoice() {

        System.out.println("Выбери файл для шифровки");
        Scanner scanner = new Scanner(System.in);
        originFilePath = Path.of(scanner.nextLine());
        String filePathFrom = originFilePath.toString();
        System.out.println("Куда сохранить файл?");
        cypherFilePath = Path.of(scanner.nextLine());
        String filePathTo = cypherFilePath.toString();
        if (Files.isRegularFile(originFilePath) && Files.isRegularFile(cypherFilePath)) {

            cypher(filePathFrom, filePathTo);

        } else{
            System.out.println("Упс! Что-то пошло не так! Убедись, что ты указал верный путь");
        }
    }

    private void cypher(String filePathFrom, String filePathTo) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePathFrom))) {
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePathTo))) {

                while (fileReader.ready()) {
                    char ch = (char) fileReader.read();
                    fileWriter.write(ch);
                }
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException ex) {
                ex.getMessage();


        }
    }
}




