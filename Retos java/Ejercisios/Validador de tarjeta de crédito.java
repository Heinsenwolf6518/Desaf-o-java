package io.Gel.challenges;

import java.util.Scanner;

public class Validadordetarjetadecredito {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String cardNumber = getCardNumber(scanner);

            if (isCardNumberValid(cardNumber)) {
                validateCard(cardNumber);
            } else {
                System.out.println("El número de tarjeta de crédito debe contener solo dígitos.");
            }
        }
    }

    private static String getCardNumber(Scanner scanner) {
        System.out.print("Ingrese el número de tarjeta de crédito: ");
        return scanner.nextLine().replaceAll("\\s+", "");
    }

    private static boolean isCardNumberValid(String number) {
        return number.matches("\\d+");
    }

    private static void validateCard(String cardNumber) {
        if (LuhnAlgorithm.isValid(cardNumber)) {
            System.out.println("El número de tarjeta de crédito es válido.");
        } else {
            System.out.println("El número de tarjeta de crédito no es válido.");
        }
    }
}

class LuhnAlgorithm {

    public static boolean isValid(String number) {
        int sum = 0;
        boolean alter = false;

        // Recorrer el número de derecha a izquierda
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alter) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alter = !alter;
        }

        // El número es válido si la suma es un múltiplo de 10
        return sum % 10 == 0;
    }
}
