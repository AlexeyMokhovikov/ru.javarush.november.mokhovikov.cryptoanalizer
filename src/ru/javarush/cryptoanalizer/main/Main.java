package ru.javarush.cryptoanalizer.main;

import ru.javarush.cryptoanalizer.utils.BruteForce;
import ru.javarush.cryptoanalizer.utils.Cipher;
import ru.javarush.cryptoanalizer.utils.Decipher;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Привет! Это моя первая программа. Выбери, что будем делать:");
            System.out.println("1. ШИФРОВАТЬ \n2. ДЕШИФРОВАТЬ \n3. ПРИМЕНИТЬ BRUTE FORCE \n4. ХОЧУ ВЫЙТИ");

            byte choice = scanner.nextByte();

            if (choice == 1) {
                new Cipher().fileChoice();
            } else if (choice == 2) {
                new Decipher().fileChoice();
            } else if (choice == 3) {
                new BruteForce().fileChoice();
            } else if (choice == 4) {
                break;
            }

        }

    }
}
