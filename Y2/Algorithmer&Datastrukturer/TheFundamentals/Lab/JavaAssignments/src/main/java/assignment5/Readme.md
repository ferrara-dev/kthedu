# Assignment 5

## Task
Implement a generalized queue which allows the user to remove the kth element from the
queue.
#### Solution

##### Implementation
The *GeneralizedQueue* implements an iterable circular doubly linked FIFO
queue which allows the user to remove elements at a given index.

The queue utilises a sentinel node to denote the start/end of the queue.
- Elements are enqueued at the front end of the queue
- Elements are dequeued at the back end of the queue
- How the nodes will be traversed when calling the remove method
  depends on the index given in the method argument. 
  The algorithm will always traverse the nodes in as few steps as 
  possible. 
  If the queue has *n* elements and the user wants to remove an
  element at index *i* there are four possible outcomes
    1. index = 1 and the element is reached immediately
    2. index = n and the element is reached immediately
    3. index is larger than n/2 and the list is traversed backwards
    4. index is smaller or equals n/2 and the list is traversed forwards

###### Complexity
All operations except the remove method have a constant time complexity.

When it comes to time complexity of the remove method 
the worst case scenario would require N/2 iterations or recursive calls
before reaching the correct element.

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
Unit tests have been made for this class, see package assignment5 in the test folder located in 
the project directory.
