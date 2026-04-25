package org.example.utils;

public class LoanCodeGenerator {

    private static int id = 0;

    public static int getNextCode() {
        return id++;
    }
}
