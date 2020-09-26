# Assignment 6
 
 ## Task
 The task is to implement an ordered queue where elements are ordered in ascending order at insertion.
 #### Solution
 
 ##### Implementation
 The *OrderedQueue* class implements an iterable circular doubly linked list which allows the user to insert 
 elements in ascending order and remove the largest element from the queue.
 
 The algorithm that makes sure the elements are ordered at insertion
 first checks if the currently largest element pointed to by sentinel.prev
 is smaller than the new element.
  
If that is the case, the element is inserted
to the back of the queue immediately.
Else nodes will be traversed forwards until an element
larger than the element that is to be added is encountered.
The new element is then added before the larger one.
 
###### Complexity
For a queue of size N, the worst case time complexity when inserting an element would 
occur if the element that is to be inserted will be the second largest
element in the queue, it would then take N-1 operations to traverse the 
queue and reach the correct place to put the element.
 
Each time an element is enqueued a new node is created
and each Node object uses :
 48 bytes + S 
- 16 bytes of object overhead
- 8 bytes each for the references to the Item and Node objects (total 24 bytes)
- 8 bytes for the extra overhead
- S = 24 bytes for the Integer object

So the amount of arbitrary space needed to store *n* elements would be 
*M*(n) = (72)*n + constant space for the queue structure.

The big O notation for the memory complexity would then be of order n,
as *M*(n) approaches infinity when n approaches infinity.


##### Testing
The *OrderedQueueImpl* class has a main method with a
simple unit test where some enqueue/dequeue is perfomed.

There are also some unit tests that in the test folder that
are used to test the methods and functionality.