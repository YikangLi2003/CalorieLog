package org.example;

import org.example.utils.Logger;
import org.example.utils.PropertyAccessor;
import org.example.utils.Timestamp;

public class Main {
    public static void main(String[] args) {
        Logger.getInstance().log(Logger.Level.INFO, "This is a piece of information: %s %s.", "info 1", "info 2");
        Logger.getInstance().log(Logger.Level.DEBUG, "Trying to find bugs by invoking these methods.");
        Logger.getInstance().log(Logger.Level.WARNING, "It seems that there is a bug! %d", 123);
        Logger.getInstance().log(Logger.Level.ERROR, "Actually no error occurs");
        Logger.getInstance().closeLogFile();
        Logger.getInstance().log(Logger.Level.INFO, "This info should not appear in log file");


        System.out.println("Time right now: " + Timestamp.getInstance().get());

        System.out.println("Property Accessor:");
        System.out.println("http.server.port=" + PropertyAccessor.getInstance().getProperty("http.server.port"));
        System.out.println("database.url=" + PropertyAccessor.getInstance().getProperty("database.url"));
        System.out.println("timestamp.pattern=" + PropertyAccessor.getInstance().getProperty("timestamp.pattern"));
    }
}