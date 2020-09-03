package A1;

import E2.BasicStack;
import datastructure.Stack;

public class ParenthesesStack extends Parentheses{

    @Override
    public boolean checkBalanced() {
        Stack<Character> stack = new BasicStack<>();

        for(int i = 0; i < super.expression.length(); i++){
            char current = super.expression.charAt(i);
            if(super.isOpening(current)){
                stack.push(current);
            }
            else if(super.isClosing(current)){
                if(stack.pop() == super.findMatchingOpening(current))
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }

}
