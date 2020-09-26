# Assignment 2

### Task
Implement a recursive and an iterative version of a function which reads characters from
standard input until a newline character is read and then prints them to standard output in reverse order. 
#### Solution
The IOUtils class is responsible for reading and writing characters to standard input and -output.


Both the recursive and the iterative part of the task has been solved using a stack data structure.

The contract of the operation, is defined by the abstract *ReadAndReverseOperation* class and implemented as  
*IterativeOperation* and *RecursiveOperation*.

In short, characters are read one by one from standard input until a new line character ('\n') is read, or the end of the stream is reached.

##### Recursive solution
The recursive method that is used to solve the task reads a character from stdin when entering the method and then checks if its valid.
If the character is valid, it is pushed to a stack and then a recursive call is made to read the next character.

This goes on until an invalid character is read. 
When the function reads and invalid character, it will exit and return to its caller, which then goes on to print the character that was pushed 
last, resulting in an output that is in reverse order relative to the input.

Creating an arbitrary stack was not really necessary when solving this task, 
the fact that the function is recursive means that the callstack holds the last function call and all of its variables.

##### Iterative solution
The iterative method uses the same approach as the recursive, but instead has two loops after each other.
The first loop will push all read characters to a stack data structure until a newline character or EOF is encountered.

The loop that comes after that will then pop the characters and print them until the stack is empty.


###### Complexity
If the print method call is removed, the time complexity of push and pop is constant, 
as insertions and removals are done at the front of the queue no mather how many elements that are currently in the queue.

The time complexity for the two functions will therefore both be of a linear order,
the iterative will result in a time complexty *Ti*(n) = 2N and the recursive *Tr*(n) = N.

The amount of space that is used by each function will also increase linearly, however 
the recursive one will store every single call on the call stack, making it 
more expensive than the iterative alternative.

The amount of arbitrary space as a function of number of elements 
used for the two methods is calculated as follows :
Each time an element is enqueued a new node is created

and each Node object uses :
 48 bytes + S 
- 16 bytes of object overhead
- 8 bytes each for the references to the Item and Node objects (total 16 bytes)
- 8 bytes for the extra overhead
- S = 24 for the Character object

So the amount of arbitrary space needed to store *n* elements would be 
*M*(n) = 64*n + constant space for the stack structure.

The big O notation for the memory complexity would then be of order n,
as *M*(n) approaches infinity when n approaches infinity.
