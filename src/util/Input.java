package util;

import java.util.Scanner;

public final class Input {

    private Input() {}

    public static int readInt(Scanner sc, String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            String raw = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(raw);

                if (value < minInclusive || value > maxInclusive) {
                    throw new IllegalArgumentException(
                            "Valor fuera de rango (" + minInclusive + " - " + maxInclusive + ").");
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Error: debes introducir un numero entero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static String readNonEmptyString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Error: el texto no puede estar vacio.");
        }
    }
}

