package org.example;

import org.example.utils.Logger;
import org.example.utils.Timestamp;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(Timestamp.get());
        System.out.println(Timestamp.get());
        String workingDir = System.getProperty("user.dir");
        System.out.println("当前工作目录：" + workingDir);

        Logger logger = new Logger("httpserverlog.txt");

        logger.log(Logger.Level.ERROR, "Error No.%d occurred.", 5);
        logger.log(Logger.Level.INFO, "This is just a piece of message.");

        logger.closeLogFile();
    }
}
