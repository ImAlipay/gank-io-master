package com.example.lib;

public class MyClass {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            if (i==1000) {
                continue;
            }
            System.out.println("I'm "+i);
        }
    }
}
