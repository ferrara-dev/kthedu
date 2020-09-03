
import operation.IterativeOperation;
import operation.RecursiveOperation;
import operation.ReadAndReverseOperation;

public class Main {

    /**
     * Main method that the program is run from
     * If the first element in the command line argument equals
     * "rec", the operation will be performed recursively, using .
     * else
     * it will perform the operation by iteration.
     *
     * @param args Command line argument
     */
    public static void main(String... args) {
        ReadAndReverseOperation app;
        if (args.length > 0 && args[0].equals("rec"))
            app = new RecursiveOperation();

        else {
            app = new IterativeOperation();
        }
        run(app);
    }

    private static void run(ReadAndReverseOperation app) {
        app.readStdin();
        app.writeStdout();
    }
}
