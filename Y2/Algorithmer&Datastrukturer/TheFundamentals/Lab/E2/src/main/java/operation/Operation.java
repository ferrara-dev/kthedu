package operation;

import io.InputReader;
import io.OutputWriter;

import java.io.IOException;

public interface Operation {
    void readStdin(InputReader inputReader) throws IOException;
    void writeStdout(OutputWriter outputWriter) throws IOException;
}
