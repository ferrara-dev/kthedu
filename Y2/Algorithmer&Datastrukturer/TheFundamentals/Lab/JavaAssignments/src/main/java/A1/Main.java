package A1;

public class Main {


    public static void main(String...args){
        Parentheses parentheses = new ParenthesesRecursive();
        long start = System.nanoTime();
        test(parentheses);
        long stop = System.nanoTime();
        System.out.println("ParenthesesRecursive "+(stop-start) );
        parentheses = new ParenthesesStack();
        start = System.nanoTime();
        test(parentheses);
        stop = System.nanoTime();
        System.out.println("ParenthesesStack "+(stop-start) );
    }

    private static void test(Parentheses parentheses){
        String [] testStrings = {"( ()(", "()", "(())", "[]", "[", "[)", "[ {} ] () () ()"};
        for(int i = 0; i < testStrings.length; i++) {
            parentheses.setExpression(testStrings[i]);
            boolean isBalanced = parentheses.checkBalanced();
            if (isBalanced)
                System.out.println(testStrings[i] + " is balanced !");
            else
                System.out.println(testStrings[i] + " is NOT balanced");
        }
    }
}
