package console.io;

import core.inverseofcontrol.annotations.Singleton;

import java.util.Scanner;

@Singleton
public class InputManager {

    private final Scanner scanner;

    public InputManager() {
        scanner = new Scanner(System.in);
    }

    private void printMessageNoNewLine(String msg) {
        System.out.print(msg);
    }

    public int getIntFromStandardInput(String msg) {
        printMessageNoNewLine(msg + ": ");
        return getIntFromStandardInput();
    }

    public int getIntRangedFromStandardInput(String msg, int start, int end) {
        String msg1 = msg + String.format("range from %d to %d", start, end) + ": ";
        printMessageNoNewLine(msg1);
        int v = getIntFromStandardInput();

        while (v < start || v > end) {
            System.out.printf("%d is not in range from %d to %d", v, start, end);
            printMessageNoNewLine(msg1);
            v = getIntFromStandardInput();
        }

        return v;
    }

    public int getIntFromStandardInput() {
        Integer i = null;

        while (i == null) {
            try {
                i = scanner.nextInt();
            } catch (Exception e) {
                scanner.next();
                printMessageNoNewLine("No integer value. Try again: ");
            }
        }
        return i;
    }

    public String getStringFromStandardInput(String msg) {
        printMessageNoNewLine(msg + ": ");
        return getStringFromStandardInput();
    }


    public String getStringFromStandardInput() {
        String s = scanner.nextLine();

        while (s.isEmpty()) {
            s = scanner.nextLine();
        }

        return s;

    }


}
