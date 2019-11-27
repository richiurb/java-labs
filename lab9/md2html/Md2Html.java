package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        MdParser parser = new MdParser();

        try {
            CustomScanner sc = new CustomScanner(
                new InputStreamReader(
                        new FileInputStream(args[0]), 
                        "UTF-8"
                    )
                );

            try {
                while (sc.hasNextLine()) {
                    parser.parseLine(sc.nextLine());
                }

                saveHtml(args[1], parser.getRoot().getHtml());
            } finally {
                sc.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Input file not found");
        } catch (UnsupportedEncodingException ex) {
            System.err.println("UTF-8 encoding is not supported");
        } catch (IOException ex) {
            System.err.println("Input is invalid!");
        }
    }

    private static void saveHtml(String filepath, String html) {
        try {
            BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(filepath),
                    "UTF-8"
                )
            );

            try {
                bw.write(html);
            } finally {
                bw.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Output file not found");
            return;
        } catch (UnsupportedEncodingException ex) {
            System.err.println("UTF-8 encoding is not supported");
            return;
        } catch (IOException ex) {
            System.err.println("Output is invalid!");
            return;
        }
    }
}