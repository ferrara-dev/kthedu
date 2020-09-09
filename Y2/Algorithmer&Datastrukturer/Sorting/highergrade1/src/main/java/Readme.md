# Higher grade assignment 1

### Task
Implement a program which takes as input a series of parentheses , that is a series of the
characters: '(', ')', '[', ']', '{', '}'. 
The program should determine if the parentheses are balanced or not.
#### Solution
###### The algorithm
The algorithm uses the following steps:

    1. Check if the string begins with a closing bracket, if it does
       the expression can not be balanced and false is returned.
       
    2. Create a stack which will hold opening brackets.
    
    3. Traverse the string character by character.
    
    4. If the current character is a opening bracket '(' OR '{' OR '['
       push it to the stack.
       
    5. If the current character is a closing bracket ‘)’ or ‘}’ or ‘]’
       then pop from stack and if the popped character is the matching 
       starting bracket continue, else expression is not balanced.
       
    6. When the string has been traversed, if the stack is not empty
       the expression can not be balanced.
       
###### Pseudo code
```
checkExpression(expression, len) :
      if expression[0] is closing parentheses:
         expression is unbalanced;

      stack = stack of characters;
      currentIndex = 0;
      for( currentIndex < len; currentIndex += 1; ):
         currentChar = expression[currentIndex]
         if currentChar is opening parentheses:
            push currentChar to stack;
         else if currentChar is closing parentheses:
            closing = currentChar
            if most recently pushed opening matches closing:
                pop character from stack;
            else
                character is unbalanced;
      end while;

      if stack is empty
        expression is balanced;
      else
        expression is not balanced;
}
```
###### Time complexity
The time complexity of the algorithm will depend on:
- The number of characters in the evaluated expression
- The number of comparisons performed per character


The cost of every if and if else statement 
as well as the push, pop and peek operations of the stack are constant.

The worst case scenario for any input size N, would be that the expression only has brackets 
and is balanced, meaning and that every opening bracket has a matching closing.

That would mean that half of the iterations 
would result in 2 operations being performed

1. entering the first if statement
2. pushing the opening bracket to the stack

The other half would result in 5 operations being performed
1. Executing the first if statement
2. go on to execute the following if else
3. enter the if else and perform a peek operation
4. execute another if statement
5. pop the opening parentheses from the stack.

The algorithm will iterate the length of the input string,
so the time cost will grow in linear proportion to the growth 
of the string length.

Let N be the number of characters in the expression.
The loop increments the value i by 1 in every iteration from 0 till N-1 ([0, 1, 2, …, n-1])

So *i* is first 0, then 1, then 2, …, then n-1. 
This means the loop increment statement i+=1 runs
a total of N times.

The comparison statement *i < len*  runs n+1 times.
The time complexity of the for loop is then
n + n + 1= 2n+2n+n+1= 2n+1.

Including the operations performed in the loop body,
the total time complexity *T(N)*, would be 
*T(N)* = (2N + 2) + (2N)/2 + (5N)/2 = 5,5N + 1

Or if the loop operations are disregarded:
*T(N)* = 7*N/2 = 3,5N


As N approaches infinity, *T(N)* approaches N so the
Big O order is of linear size: O(N) = N;

###### Space complexity
The worst case scenario for an input string of lenght N 
would occur if the string contains only opening brackets.

The alogrithm would then traverse the whole string
character by character, and push every single one 
to the stack.

This specific implementation uses a linked list stack
and whereas a new node object will have to be created every iteration.

Each node would take up 40 bytes 
- 16 for object overhead
- 8 for reference to the next node
- 2 for the primitive char
- 8 bytes for extra overhead 
- 6 for padding

Thus, the amount of arbitrary space that would be needed in 
the worst case scenario would be 40bytes * N.

Giving the algorithm a space complexity of linear order.