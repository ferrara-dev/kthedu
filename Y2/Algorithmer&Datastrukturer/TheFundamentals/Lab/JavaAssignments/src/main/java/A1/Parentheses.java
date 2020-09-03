package A1;

public abstract class Parentheses {
    protected String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public abstract boolean checkBalanced();

    protected boolean isClosing(char c){
        return c == ')' || c == ']' || c == '}';
    }

    protected boolean isOpening(char c){
        return c== '(' || c == '[' || c == '{';
    }

    protected char findMatchingOpening(char c){
        if(c == ')')
            return '(';
        else if(c == ']')
            return '[';
        else if(c == '}')
            return '{';
        else
            return Character.MIN_VALUE;
    }

    protected char findMatchingClosing(char c){
        if(c == '(')
            return ')';
        else if(c == '[')
            return ']';
        else if(c == '{')
            return '}';
        else
            return Character.MIN_VALUE;
    }
}
