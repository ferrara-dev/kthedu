# Assignment 4

## Task
The task is to implement a generic iterable circular linked list which allows the user to insert and remove
elements to/from the front and back end of the queue. 
#### Solution

##### Implementation
The *GenericQueue* implements an iterable circular doubly linked list which allows the user to insert 
and remove elements to/from the front and back end of the queue.

The queue utilises a sentinel node to denote the start/end of the queue.
- Elements inserted and removed with addFirst/removeFirst will add/remove 
the node pointed to by sentinel.next.
- Elements inserted and removed with addLast/removeLast will add/remove
the node pointed to by sentinel.prev

###### Complexity
If print is removed from the remove/add methods of this class, all operations
are performed with constant time complexity as the number of elements in the 
queue does not effect the amount of operations performed to remove/insert and elements.
 
Each time an element is enqueued a new node is created
and each Node object uses :
 48 bytes + S 
- 16 bytes of object overhead
- 8 bytes each for the references to the Item and Node objects (total 24 bytes)
- 8 bytes for the extra overhead
- S = (space for the object type that is stored)

So the amount of arbitrary space needed to store *n* elements would be 
*M*(n) = (48+S)*n + constant space for the queue structure.

The big O notation for the memory complexity would then be of order n,
as *M*(n) approaches infinity when n approaches infinity.

##### Testing
Instructions for the test is read from standard input. 
Instructions available for this particular test is as following:
- Add first x(f+)
    - x = number of times the instruction is repeated
    - For example, the instruction 3(f+) will add 3 random numbers to 
    the front of the list.
- Add last x(l+)
    - x = number of times the instruction is repeated
    - For example, the instruction 3(l+) will add 3 random numbers to 
        the back of the list.
- Remove last x(l-)
    - x = number of times the instruction is repeated
    - For example, the instruction 2(l-) will remove 2 elements from 
        the back of the list.
- Example use : 2f+ l+ 2f- 
    - 2f+ => add two elements to the front
    - l+  => add one element to the back
    - 2f- => remove two elements from the front