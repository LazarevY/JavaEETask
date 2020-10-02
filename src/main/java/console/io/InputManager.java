package console.io;

import java.util.Scanner;

public class InputManager {

    private Scanner scanner;

    private static InputManager manager;

    public static InputManager getInstance(){
        if (manager == null)
            manager = new InputManager();
        return manager;
    }

    public InputManager() {
        scanner = new Scanner(System.in);
    }

    private void printMessageNoNewLine(String msg){
        System.out.print(msg);
    }

    public int getIntFromStandardInput(String msg){
        printMessageNoNewLine(msg+ ": ");
        return getIntFromStandardInput();
    }

    public int getIntRangedFromStandardInput(String msg, int start, int end){
        String msg1 = msg + String.format("range from %d to %d", start, end) + ": ";
        printMessageNoNewLine(msg1);
        int v = getIntFromStandardInput();

        while (v < start || v > end){
            System.out.printf("%d is not in range from %d to %d", v, start, end);
            printMessageNoNewLine(msg1);
            v = getIntFromStandardInput();
        }

        return v;
    }

    public int getIntFromStandardInput(){
        return scanner.nextInt();
    }

    public String getStringFromStandardInput(String msg){
        printMessageNoNewLine(msg+": ");
        return getStringFromStandardInput();
    }


    public String getStringFromStandardInput(){
        return scanner.nextLine();
    }


}
