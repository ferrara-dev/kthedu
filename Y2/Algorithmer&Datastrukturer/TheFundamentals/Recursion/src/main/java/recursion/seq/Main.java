package recursion.seq;

public class Main {
    /**
     * Calculate the sum of a sequence from 1 to n
     * n is given as a commandline argument
     */
    public static void main(String ...args){
        Sequence sequence = new Sequence();
        System.out.println(sequence.triangle(Integer.parseInt(args[0])));
    }

}
