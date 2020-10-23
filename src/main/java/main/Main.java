package main;

import core.ObjectFactory;
import core.ObjectFactoryImpl;
import lombok.SneakyThrows;

public class Main {


    @SneakyThrows
    public static void main(String[] args){

        ObjectFactory objectFactory =
                ObjectFactoryImpl.getInstance();

    }
}
