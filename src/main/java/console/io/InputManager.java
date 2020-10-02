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

    public int getIntFromStandardInput(){
        return scanner.nextInt();
    }

    public String getStringFromStandardInput(String msg){
        printMessageNoNewLine(msg+": ");
        return scanner.next();
    }

    public String getStringFromStandardInput(){
        return scanner.next();
    }


}
