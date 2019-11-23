package md2html;

import java.io.*;

public class Logger {
    private static final String PATH = "log";

    public static void writeLine(String log) {
        try {
            File file = new File(PATH);
            FileWriter fr = new FileWriter(file, true);
            fr.write(log + "\n");
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}