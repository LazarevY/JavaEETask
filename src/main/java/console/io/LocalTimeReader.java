package console.io;

import core.annotations.InjectByType;
import core.annotations.Singleton;

import java.time.DateTimeException;
import java.time.LocalTime;

@Singleton
public class LocalTimeReader implements ConsoleClassReader<LocalTime>{

    @InjectByType
    private InputManager inputManager;

    @Override
    public LocalTime safeRead(String msg) {
        LocalTime time = null;

        while (time == null){
            String s = inputManager
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
