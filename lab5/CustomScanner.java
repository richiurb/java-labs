import java.io.*;
import java.util.NoSuchElementException;

public class CustomScanner {
    private final int LINE_FEED = 10;
    private final int ENTER_KEY_CODE = 13;
    private final int SPACE_KEY_CODE = 32;

    private final Reader inputSource;

    private String input;
    private int currentCharIndex;
    private String nextWord;

    public CustomScanner(InputStream source) {
        this.inputSource = new InputStreamReader(source);
    }

    public void close() throws IOException {
        inputSource.close();
    }

    public boolean hasNext() throws IOException {
        if (nextWord != null) {
            return true;
        }

        if (input == null || currentCharIndex == input.length()) {
            currentCharIndex = 0;
            input = receiveInput();

            if (input == null) {
                return false;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean wordStarted = false;

        while (currentCharIndex < input.length()) {
            char currentChar = input.charAt(currentCharIndex);

            if (isDelimiter(currentChar)) {
                if (wordStarted) {
                    break;
                } 
            } else {
                wordStarted = true;
                sb.append(currentChar);
            }

            currentCharIndex++;
        }

        if (sb.length() > 0) {
            nextWord = sb.toString();
        } else {
            return hasNext();
        }

        return true;
    }

    public boolean hasNextInt() throws IOException {
        try {
            if (hasNext()) {
                Integer.parseInt(nextWord);
                return true;
            }
        } catch (NumberFormatException e) {
        }

        return false;
    }

    public boolean hasNextLine() throws IOException {
        if (nextWord != null) {
            return true;
        }

        if (input == null || currentCharIndex == input.length()) {
            currentCharIndex = 0;
            input = receiveInput();

            if (input == null) {
                return false;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (currentCharIndex < input.length()) {
            char currentChar = input.charAt(currentCharIndex);
            sb.append(currentChar);
            currentCharIndex++;
        }

        if (sb.length() > 0) {
            nextWord = sb.toString();
        } else {
            return hasNextLine();
        }

        return true;
    }

    public String next() throws NoSuchElementException, IOException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        String wordToReturn = nextWord.length() > 0 ? nextWord : null;
        nextWord = null;

        return wordToReturn;
    }

    public int nextInt() throws NoSuchElementException, IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException();
        }

        int intToReturn = Integer.parseInt(nextWord);
        nextWord = null;

        return intToReturn;
    }

    public String nextLine() throws NoSuchElementException, IOException {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }

        String wordToReturn = nextWord.length() > 0 ? nextWord : null;
        nextWord = null;

        return wordToReturn;
    }

    private String receiveInput() throws IOException {
        StringBuilder sb = new StringBuilder();
        int nextCharCode = 0;

        while (true) {
            nextCharCode = inputSource.read();

            if (nextCharCode == ENTER_KEY_CODE) {
                break;
            }

            if (sb.length() == 0 && nextCharCode == -1) {
                return null;
            }

            if (nextCharCode != LINE_FEED) {
                sb.append((char)nextCharCode);
            }
        }

        return sb.toString();
    }

    private boolean isDelimiter(char value) {
        return (int)value == SPACE_KEY_CODE;
    }
}