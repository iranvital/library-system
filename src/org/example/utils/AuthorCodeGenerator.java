package org.example.utils;

public class AuthorCodeGenerator {

    private static int id = 0;

    public static int getNextCode() {
        return id++;
    }
}
