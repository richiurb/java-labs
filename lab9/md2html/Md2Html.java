package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        MdRootNode root;
        try {
            CustomScanner sc = new CustomScanner(
                new InputStreamReader(
                        new FileInputStream(args[0]), 
                        "UTF-8"
                    )
                );

                MdParser parser = new MdParser();
            try {
                while (sc.hasNextLine()) {
                    parser.parseLine(sc.nextLine());
                }
                root = parser.getRoot();
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
        saveHtml(args[1], root.getHtml());
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
        } catch (UnsupportedEncodingException ex) {
            System.err.println("UTF-8 encoding is not supported");
        } catch (IOException ex) {
            System.err.println("Output is invalid!");
        }
    }
}