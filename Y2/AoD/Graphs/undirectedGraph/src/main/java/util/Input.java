package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Input {
    ///// begin: section (1 of 2) of code duplicated from In to StdIn.

    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;

    // the default token separator; we maintain the invariant that this value
    // is held by the scanner's delimiter between calls
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // makes whitespace characters significant
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    // used to read the entire input. source:
    // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    //// end: section (1 of 2) of code duplicated from In to StdIn.

    private Scanner scanner;

    public Input(File file){
        if (file == null)
            throw new IllegalArgumentException("file argument is null");
        try {
            // for consistency with StdIn, wrap with BufferedInputStream instead of use
            // file as argument to Scanner
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }


    ////  begin: section (2 of 2) of code duplicated from In to StdIn,
    ////  with all methods changed from "public" to "public static".

    /**
     * Returns true if input stream is empty (except possibly whitespace).
     * Use this to know whether the next call to {@link #readString()},
     * {@link #readDouble()}, etc will succeed.
     *
     * @return {@code true} if this input stream is empty (except possibly whitespace);
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return !scanner.hasNext();
    }

    /**
     * Returns true if this input stream has a next line.
     * Use this method to know whether the
     * next call to {@link #readLine()} will succeed.
     * This method is functionally equivalent to {@link #hasNextChar()}.
     *
     * @return {@code true} if this input stream has more input (including whitespace);
     *         {@code false} otherwise
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Returns true if this input stream has more input (including whitespace).
     * Use this method to know whether the next call to {@link #readChar()} will succeed.
     * This method is functionally equivalent to {@link #hasNextLine()}.
     *
     * @return {@code true} if this input stream has more input (including whitespace);
     *         {@code false} otherwise
     */
    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }


    /**
     * Reads and returns the next line in this input stream.
     *
     * @return the next line in this input stream; {@code null} if no such line
     */
    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }

    /**
     * Reads and returns the next character in this input stream.
     *
     * @return the next {@code char} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     */
    public char readChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        try {
            String ch = scanner.next();
            assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
                    + " Please contact the authors.";
            scanner.useDelimiter(WHITESPACE_PATTERN);
            return ch.charAt(0);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'char' value from the input stream, "
                    + "but no more tokens are available");
        }
    }


    /**
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     */
    public String readAll() {
        if (!scanner.hasNextLine())
            return "";

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }


    /**
     * Reads the next token from this input stream and returns it as a {@code String}.
     *
     * @return the next {@code String} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     */
    public String readString() {
        try {
            return scanner.next();
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'String' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code int},
     * and returns the {@code int}.
     *
     * @return the next {@code int} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as an {@code int}
     */
    public int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code double},
     * and returns the {@code double}.
     *
     * @return the next {@code double} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code double}
     */
    public double readDouble() {
        try {
            return scanner.nextDouble();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'double' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read a 'double' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code float},
     * and returns the {@code float}.
     *
     * @return the next {@code float} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code float}
     */
    public float readFloat() {
        try {
            return scanner.nextFloat();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'float' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read a 'float' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code long},
     * and returns the {@code long}.
     *
     * @return the next {@code long} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code long}
     */
    public long readLong() {
        try {
            return scanner.nextLong();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'long' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read a 'long' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code short},
     * and returns the {@code short}.
     *
     * @return the next {@code short} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code short}
     */
    public short readShort() {
        try {
            return scanner.nextShort();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'short' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read a 'short' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code byte},
     * and returns the {@code byte}.
     * <p>
     *
     * @return the next {@code byte} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code byte}
     */
    public byte readByte() {
        try {
            return scanner.nextByte();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'byte' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read a 'byte' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code boolean}
     * (interpreting either {@code "true"} or {@code "1"} as {@code true},
     * and either {@code "false"} or {@code "0"} as {@code false}).
     *
     * @return the next {@code boolean} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token cannot be parsed as a {@code boolean}
     */
    public boolean readBoolean() {
        try {
            String token = readString();
            if ("true".equalsIgnoreCase(token))  return true;
            if ("false".equalsIgnoreCase(token)) return false;
            if ("1".equals(token))               return true;
            if ("0".equals(token))               return false;
            throw new InputMismatchException("attempts to read a 'boolean' value from the input stream, "
                    + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'boolean' value from the input stream, "
                    + "but no more tokens are available");
        }
    }

    /**
     * Reads all remaining tokens from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining tokens in this input stream, as an array of strings
     */
    public String[] readAllStrings() {
        // we could use readAll.trim().split(), but that's not consistent
        // since trim() uses characters 0x00..0x20 as whitespace
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;
        String[] decapitokens = new String[tokens.length-1];
        for (int i = 0; i < tokens.length-1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }

    /**
     * Reads all remaining lines from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining lines in this input stream, as an array of strings
     */
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[lines.size()]);
    }


    /**
     * Reads all remaining tokens from this input stream, parses them as integers,
     * and returns them as an array of integers.
     *
     * @return all remaining lines in this input stream, as an array of integers
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as longs,
     * and returns them as an array of longs.
     *
     * @return all remaining lines in this input stream, as an array of longs
     */
    public long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Long.parseLong(fields[i]);
        return vals;
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as doubles,
     * and returns them as an array of doubles.
     *
     * @return all remaining lines in this input stream, as an array of doubles
     */
    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }

    ///// end: section (2 of 2) of code duplicated from In to StdIn */

    /**
     * Closes this input stream.
     */
    public void close() {
        scanner.close();
    }
}
