package main;
import logic.console.app.ConsoleApp;
import org.apache.tools.ant.types.Commandline;

public class Main {
    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();

        app.execCommand("view birthday 'day != 0'");

    }
}
