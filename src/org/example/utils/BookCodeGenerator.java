package org.example.utils;

public class BookCodeGenerator {

    private static int id = 0;

    public static int getNextCode() {
        return id++;
    }
}
