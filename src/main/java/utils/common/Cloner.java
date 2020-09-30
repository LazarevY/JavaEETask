package utils.common;

import java.io.*;
import java.util.Objects;

public class Cloner {
    public static <T> T clone(T orig) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous;
        try {
            ous = new ObjectOutputStream(baos);
            Objects.requireNonNull(ous).writeObject(orig);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois;
            ois = new ObjectInputStream(bais);
            T clone = (T) ois.readObject();
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
