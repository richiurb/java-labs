package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        MdParser parser = new MdParser();
        Md2HtmlWriter md2html = new Md2HtmlWriter();

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

                writeHtml(args[1], md2html.getHtml(parser.getRoot()));
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

    private static void writeHtml(String filepath, String html) {
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