package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class OutputWriter {
    private PrintStream printStream;

    public OutputWriter(PrintStream printStream){
        this.printStream = printStream;
    }

    public void putChar(char c) throws IOException {
        printStream.print(c);
    }

}
