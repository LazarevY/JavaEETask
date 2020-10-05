package console.io;

import java.time.DateTimeException;
import java.time.LocalTime;

public class LocalTimeReader implements ConsoleClassReader<LocalTime>{
    @Override
    public LocalTime safeRead(String msg) {
        LocalTime time = null;

        while (time == null){
            String s = InputManager
                    .getInstance()
                    .getStringFromStandardInput(msg);
            try {
                time = LocalTime.parse(s);
            }
            catch (DateTimeException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
                time = null;
            }
        }

        return time;
    }
}
