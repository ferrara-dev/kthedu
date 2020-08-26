import io.InputReader;
import io.OutputWriter;
import operation.IterativeOperation;
import operation.RecursiveOperation;
import operation.Operation;

import java.io.IOException;

public class Main {

    public static void main(String... args) {
        Operation app;
        if (args.length > 0 && args[0].equals("rec"))
            app = new RecursiveOperation();

        else {
            app = new IterativeOperation();
        }
        run(app);
    }

    private static void run(Operation app) {
        try {
            app.readStdin(new InputReader(System.in));
            app.writeStdout(new OutputWriter(System.out));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
