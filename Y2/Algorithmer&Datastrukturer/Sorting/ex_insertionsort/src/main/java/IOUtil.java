import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IOUtil {
    private static InputStream inputStream = System.in;
    private static Scanner scanner = new Scanner(inputStream);

    /**
     * Read a line from standard input and convert it into an int array.
     *
     * @return
     */
    public static Integer[] readIntArray() {
        String line = "";
        if (scanner.hasNextLine())
            line = scanner.nextLine();
        String [] ints = line.split(" ");
        ArrayList<Integer> lst = new ArrayList<Integer>();
        for(int i = 0; i < ints.length; i++){
            try{
                lst.add(Integer.parseInt(ints[i]));
            } catch (NumberFormatException e){
                System.err.println("Could not parse " + ints[i] + "\n" +
                        "Not an integer");
                continue;
            }
        }
        return lst.toArray(new Integer [lst.size()]);
    }

}
