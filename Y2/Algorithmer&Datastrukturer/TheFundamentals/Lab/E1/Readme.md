# Assignment 1
## Task
In C implement a recursive and an iterative version of a function which reads characters from stdin until a newline character is read and then prints them on stdout in reverse order.
## Solution
If the command line argument equals "rec", the program will run the recursive
solution, else the iterative will be run.

The program reads the characters until EOF or a new line character is read, and prints them in reverse to stdout.

### Iterative solution
The iterative solution uses an arbitrary stack data structure to solve the
problem. 

The data structure is implemented as a linked list with nodes that consist 
of a typedef struct that holds a char value and a pointer to the next node.

The stack structure holds a pointer to the head node and function pointers
to the push, pop and is empty functions.

All of these functions take a pointer to itself as argument in order to
make manipulation of the stack possible.

To perform the task with the stack, characters are read from stdin and 
pushed to the stack until EOF or a newline character is read.

The characters are then popped from the stack and printed to stdout
until the stack is empty.

### Recursive solution
The recursive solution does not require any arbitrary data structures, 
and was much quicker to implement. 

The fact that the function is calling itself makes it possible to reverse the
characters using the call stack.

Every time a recursive call is made, the current function call is pushed to the call stack.
When the end of line or file is reached, the function will return to its previous caller,
popping it from the the call stack.
It will then continue after the line of its last recursive call
and print the char to stdout before returning to its previous caller,
meaning that the character that was read last will be printed first effectively
printing the input in reverse.

#### Recursive vs Iterative solution
The downside to the recursive solution, is that it can cause the 
call stack to overflow if to many recursive calls are made, while the
iterative solution can handle much larger inputs without any problem.

The biggest advantage with the recursive solution was that it was 
very readable and easy to implement while the iterative required 
an arbitrary data structure if the size of the input was not 
known before hand.


### Test
The program can be tested by redirecting standard input to 
the txt file "input.txt" in the src directory.

The program will then read the characters 'D' 'E' 'S' 'R' 'E' 'V' 'E' 'R'

and print them to the console in reverse order 'R' 'E' 'V' 'E' 'R' 'S' 'E' 'D'