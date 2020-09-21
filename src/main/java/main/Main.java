package main;
import org.apache.tools.ant.types.Commandline;

public class Main {
    public static void main(String[] args) {
        String []ar = Commandline.translateCommandline("view 'day>12 & year=2020'");
        for (String s: ar)
            System.out.println(s);

    }
}
