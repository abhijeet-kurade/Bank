package com.example.bank.Services;

import org.apache.tomcat.jni.Time;

public class Utility {

    public static void sleep(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
