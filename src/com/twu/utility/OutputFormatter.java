package com.twu.utility;

public class OutputFormatter {
    public static void printInfo(String s) {
        System.out.printf("====== %s ======\n", s);
    }

    public static void printError(String s) {
        System.out.printf("!!!!!! %s !!!!!!\n", s);
    }
}
