package E3;

import datastructure.CircularQueue;
import datastructure.Queue;
import util.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Samuel Ferrara 19940412-1395
 * Doubly linked circular list Implementation of a generic queue enforcing
 * the First In First Out policy.
 *
 * The doubly linked list is implemented using an empty sentinel node to denote the
 * start / beginning of the list.
 *
 * enqueue --> [x]->[x][x][x][x]
 *             [x][x][x][x]->[x] --> dequeue
 * @param <Item>
 */
public class QueueImpl<Item> extends CircularQueue<Item> {

    /**
     * Simple unit test of <code> QueueImpl </code> class.
     *
     * Test data is received from a text file containing a sequence of characters.
     *
     * If read string equals '-' dequeue() is called
     * If read string is a digit, it is enqueued by calling enqueue(Item item)
     * The file is read until <code> scanner </code> obj does not have any
     * string left to read
     *
     * The test will also test the iterator implementation.
     */
    public static class Test{
        private CircularQueue<Integer> queue = new QueueImpl<>();

        public static void main(String ...args) throws IOException {
            Test test = new Test();
            test.test(new Scanner(System.in));
            Scanner scanner = new Scanner(System.in);
            test.testIter();
        }


        private void test (Scanner scanner){
            scanner.useDelimiter(" ");
            if(scanner.hasNext()){
                String s = scanner.next();
                boolean isNumeric = s.chars().allMatch( Character::isDigit );
                if(isNumeric){
                    queue.enqueue(Integer.parseInt(s));
                }

                else if(s.equals("-"))
                    queue.dequeue();

                test(scanner);
            }
        }

        private void test(InputStream inputStream) throws IOException {
            char c  = (char) inputStream.read();
            if(c == '\n' || c == (char) -1)
                return;

            else if(Character.isDigit(c))
                queue.enqueue(Character.getNumericValue(c));

            else if(c == '-')
                queue.dequeue();

            test(inputStream);
        }

        private void testIter(){
            System.out.println("::::::::::::::: Testing iterator :::::::::::::::");
            System.out.println("Current queue state is : " + queue.toString());
            for(Integer i :queue)
                System.out.println("[ " + i + " ]");
        }

        /**
         * Test method used to iterate through the queue recursively
         * @param iterator
         */
        private void iterate(Iterator<Integer> iterator){
            if(iterator.hasNext()){
                int i = iterator.next();
                System.out.println("[ " + i + " ]");
                iterate(iterator);
            }
            else
                return;
        }
    }
}
