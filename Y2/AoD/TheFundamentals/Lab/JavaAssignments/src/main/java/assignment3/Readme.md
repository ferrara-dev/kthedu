# Assignment 3
## Task
The task is to implement a generic iterable FIFO-queue based on a double linked circular list. 
#### Solution

##### Implementation
The *Queue* interface defines the contract for a generic iterable queue and its basic operations.

An abstract *CircularQueue* class implements this contract for a queue enforcing the First In First Out policy.
Meaning the element that was first added to the queue will be removed when calling the dequeue method.

This implementation of a circular linked list makes use of a sentinelnode, that denotes the start/end of the queue.
The sentinel node is always empty.

The FIFO policy is implemented by inserting to the front of the queue, and removing from the back. 

In other words, the element that has been in the queue the longest will be at index 0 and 
will get removed when dequeue is called.

###### Complexity
If the print method call is removed, the time complexity of enqueue and dequeue is constant, 
as insertions are done to the front of the queue and removals from the back no mather how 
many elements that are currently in the queue.

The amount of arbitrary space as a function of number of elements 
used for this implementation is calculated as follows :

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
The implementation class has a main method from where a test can be run.
Input can either be redirected to a text file or be given dynamically from keyboard by the user.

The test case concerns integers only and will disregard anything other 
than plus and minus signs.

All elements of the input needs to be separated by white spaces.

If an integer is read by the test method, it will be enqueued and 
if a string containing a minus sign is read, the element that has been in the
queue for the longest will be removed.

There are also som junit tests in the in the test folder that is found
in the project directory.
