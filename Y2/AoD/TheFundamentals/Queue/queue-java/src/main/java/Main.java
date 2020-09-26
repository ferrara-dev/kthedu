import datastructure.Stack;

public class Main {

    public static void main(String ...args){
        Main main = new Main();
        main.testStack();
    }

    private void testStack(){
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i < 20; i++ ){
            if(i % 5 == 0)
                System.out.println("Popped " + stack.pop() + " from the stack !");
            else{
                stack.push(i);
                System.out.println("Pushed " + i + " to the stack !");
            }
            stack.print();
        }
    }
}
