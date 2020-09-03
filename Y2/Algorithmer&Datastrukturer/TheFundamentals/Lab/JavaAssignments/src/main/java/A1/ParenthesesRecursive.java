package A1;

public class ParenthesesRecursive extends Parentheses {
    private static final int UNBALANCED = -1;

    public boolean checkBalanced(){
        return checkBalanced(0) == expression.length();
    }

    private int checkBalanced(int n){
        if(n >= super.expression.length() -1  || super.isClosing(super.expression.charAt(n)))
            return n;
        if(isOpening(super.expression.charAt(n))){
            char c = super.findMatchingClosing(super.expression.charAt(n));
            int i = checkBalanced(n +1);
            if(super.expression.charAt(i) == c){
                return checkBalanced(i+1);
            }
            else {
                return n+1;
            }
        }
        return checkBalanced(n+1);
    }
}
