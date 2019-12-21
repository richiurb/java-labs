import java.util.*;
import java.io.*;

public class WordStatFirstIndex {
    private static LinkedHashMap<String, WordStat> wordStats;
    private static int lineNumber;

    public static void main(String[] args) {
        wordStats = new LinkedHashMap<String, WordStat>();
        lineNumber = 0;

        try {
            CustomScanner sc = new CustomScanner(
                new InputStreamReader(
                        new FileInputStream(args[0]), 
                        "UTF-8"
                    )
                );

            try {
                while (sc.hasNextLine()) {
                    lineNumber++;
                    parseLine(sc.nextLine());
                }
            } finally {
                sc.close();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Input file not found");
            return;
        } catch (UnsupportedEncodingException ex) {
            System.err.println("UTF-8 encoding is not supported");
            return;
        } catch (IOException ex) {
            System.err.println("Input is invalid!");
            return;
        }

        try {
            BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    "UTF-8"
                )
            );

            try {
                printWords(bw);
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

    private static void parseLine(String line) {
        if (line == null || line.isEmpty()) {
            return;
        }

        String lowerLine = line.toLowerCase();
        int wordIndexInLine = 0;
        int wordStartIndex = 0;
        boolean isInWord = false;

        ;
        for (int currentCharacterIndex = 0; currentCharacterIndex < lowerLine.length(); currentCharacterIndex++) {
            boolean isWordChar = isLetterCharacter(lowerLine.charAt(currentCharacterIndex));

            if (isWordChar && !isInWord) {
                wordStartIndex = currentCharacterIndex;
                isInWord = true;
                continue;
            }

            if (!isWordChar && isInWord) {
                isInWord = false;

                String word = lowerLine.substring(wordStartIndex, currentCharacterIndex);
                writeStat(word, ++wordIndexInLine);
            }
        }

        if (isInWord) {
            String word = lowerLine.substring(wordStartIndex, currentCharacterIndex);
            writeStat(word, ++wordIndexInLine);
        }
    }

    private static boolean isLetterCharacter(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }

    private static void writeStat(String word, int wordIndex) {
        WordStat wordStat = wordStats.get(word);

        if (wordStat == null) {
            wordStat = new WordStat();
            wordStats.put(word, wordStat);
        }

        wordStat.total++;

        if (wordStat.lastLine < lineNumber) {
            wordStat.indexes.add(wordIndex);
            wordStat.lastLine = lineNumber;
        }
    }

    private static void printWords(BufferedWriter bw) 
    throws IOException {
        Set<Map.Entry<String, WordStat>> wordsSet = wordStats.entrySet();
        String line = "";

        for (Map.Entry<String, WordStat> entry : wordsSet) {
            if (!line.isEmpty()) {
                bw.newLine();
            }

            line = String.format("%s %d", entry.getKey(), entry.getValue().total);

            for (int index : entry.getValue().indexes.getAll()) {
                line += " " + index;
            }

            bw.write(line);
        }
    }
}