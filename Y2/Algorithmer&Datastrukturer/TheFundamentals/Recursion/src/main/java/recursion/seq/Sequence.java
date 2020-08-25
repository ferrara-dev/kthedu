package recursion.seq;
/**
 *  Implementation of recursive example from lecture 1 Course ID1020
 */
public class Sequence {

    /**
     * Calculate the sum of all natural numbers
     * in a sequence from 0 to n recursively
     * @param n
     * @return the sum of the sequence
     */
    int triangle(int n){
        if(n <= 0)
            throw new IllegalArgumentException("Argument " + "( " + n + " )"+ " is not a natural number");
        if(n == 1)
            return 1;
        else {
            return (n + triangle(n - 1));
        }
    }
}
