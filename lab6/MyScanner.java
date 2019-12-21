import java.io.*;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class MyScanner {
    private final Reader input;
    private StringBuilder inputInString = new StringBuilder();
    private int countInInput = 0;
    private boolean flag = false; 

    public MyScanner(Reader input) {
        this.input = input;
    }

    public MyScanner(InputStream inputStreamIn) {
        this.input = new InputStreamReader(inputStreamIn);
    }

    public MyScanner(StringBuilder lineInput) {
        String inputString = lineInput.toString();
        this.input = new BufferedReader(new StringReader(inputString));
    }

    private void inputIn(Reader input) throws IOException {
        if (input != null) {

            int nextChar = input.read();

            while (nextChar != -1) {
                inputInString.append((char)nextChar);
                nextChar = input.read();
            }
            inputInString.append(' ');
            inputInString.append('\r');
        }
    }

    private boolean isTokensSeparator(char checkSymbol) {
        return (Character.isLetter(checkSymbol) || (checkSymbol == '\'') || (Character.getType(checkSymbol) == Character.DASH_PUNCTUATION)) ? false : true;
    }

    public void close() throws IOException {
        input.close();
    }

    public boolean hasNext() throws IOException {
        if (flag == false) {
            flag = true;
            inputIn(input);
        }
        int i = countInInput;
        while (i < inputInString.length()) {
            if (isTokensSeparator(inputInString.charAt(i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean hasNextInt() throws IOException {
        if (flag == false) {
            flag = true;
            inputIn(input);
        }
        int i = countInInput;
        while (i < inputInString.length()) {
            if (Character.isDigit(inputInString.charAt(i))) {
                return true;
            }
            i++;
        }
        return false;
    }
    
    public boolean hasNextLine() throws IOException {
        if (flag == false) {
            flag = true;
            inputIn(input);
        }

        int i = countInInput;

        while (i < inputInString.length()) {
            if (inputInString.charAt(i) == '\r') {
                return true;
            }
            i++;
        }

        return false;
    }

    public String next() throws IOException {
        if (hasNext()) {
            while ((countInInput < inputInString.length()) && (isTokensSeparator(inputInString.charAt(countInInput)))) {
                countInInput++;
            } 
            String returnOut = "";
            while ((countInInput < inputInString.length()) && (isTokensSeparator(inputInString.charAt(countInInput)))) {
                returnOut += inputInString.charAt(countInInput);
                countInInput++;
            }
            return returnOut;
        }
        throw new NoSuchElementException();
    }

    public int nextInt() throws IOException {
        if (hasNextInt()) {
            while (countInInput < inputInString.length() && 
            (!Character.isDigit(inputInString.charAt(countInInput)) && inputInString.charAt(countInInput) != '-')) {
                countInInput++;
            }
            String returnOut = "";
            while ((countInInput < inputInString.length()) && 
            (Character.isDigit(inputInString.charAt(countInInput)) || inputInString.charAt(countInInput) == '-')) {
                returnOut += inputInString.charAt(countInInput);
                countInInput++;
            }
            return Integer.parseInt(returnOut);
        }
        throw new NoSuchElementException();
    }

    public StringBuilder nextLine() throws IOException {
        if (hasNextLine()) {
            StringBuilder lineOut = new StringBuilder();
            while (countInInput < inputInString.length()) {
                if (inputInString.charAt(countInInput) == '\r') {
                    countInInput += 2;
                    break;
                }
                lineOut.append(inputInString.charAt(countInInput));
                countInInput++;
            }
            return lineOut;
        }
        throw new NoSuchElementException();
    }
}