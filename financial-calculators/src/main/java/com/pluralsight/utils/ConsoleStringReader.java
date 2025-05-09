package com.pluralsight.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleStringReader {

    public static final Scanner scanner = new Scanner(System.in);

    public static double getPositiveDouble() {
        double value;
        while (true) {
            try {
                value = scanner.nextDouble();
                if (value <= 0) {
                    System.out.println("Invalid input. Value should be greater than 0.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value greater than 0.");
                scanner.nextLine();
            }
        }
    }

    public static int getPositiveInt() {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Invalid input. Value should be greater than 0.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value greater than 0.");
                scanner.nextLine();
            }
        }
    }
}
