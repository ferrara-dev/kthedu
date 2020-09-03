package E2;

import E2.operation.IterativeOperation;
import E2.operation.ReadAndReverseOperation;
import E2.operation.RecursiveOperation;

public class App {
    private ReadAndReverseOperation operation;

    public App(ReadAndReverseOperation operation) {
        this.operation = operation;
    }

    public void run() {
        operation.doOperation();
    }

    /**
     * Main method that the application is run from.
     * If the first element in the command line argument equals
     * "rec", the operation will be performed recursively.
     *
     * Else it will perform the operation by iteration.
     *
     * @param args Command line argument
     */
    public static void main(String... args) {
        App app;
        if (args.length > 0 && args[0].equals("rec"))
            app = new App(new RecursiveOperation());
        else
            app = new App(new IterativeOperation());
        app.run();
    }
}
