package console.io;

public interface ConsoleClassReader<Type> {
    Type safeRead(String msg);
    default Type safeRead(){
        return safeRead("");
    }
}
