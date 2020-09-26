#include <stdio.h>
#include <stdlib.h>

typedef struct Node Node;

struct Node{
    char data;
    struct Node *next;
};

Node * createNode(char data);

/**
 * Implementation Construct function of @code{Node} struct.
 *
 * Takes a @code{char} as parameter, allocates
 * the memory space for the node and initialises
 * its variables.
 *
 * @return a pointer to the created node
 */
Node * createNode(char a){
    Node *node = malloc(sizeof(*node));
    if (!node) {
        perror("malloc failed");
        exit(1);
    }

    node->next = NULL;
    node->data = a;
    return node;
}

/**
 * Declaration of typedef @code{Stack} struct.
 *
 * The stack holds a @{Node} pointer, pointing
 * to the element at the top of the stack.
 *
 * It also holds function pointers to the methods that it uses.
 *
 * Implements a linked list stack that
 * enforces the LIFO policy,
 * meaning that when the element that was last pushed to the stacked
 * will be removed and returned when calling pop();
 *
 */
typedef struct Stack Stack;
struct Stack{
    Node *head;
    void (*push)(Stack *self, char c);
    char (*pop)(Stack *self);
    int (*isEmpty)(Stack *self);
};

Stack *new_stack();
void push(Stack *self, char c);
char pop(Stack *self);
int isEmpty(Stack *self);


void run_it();
void run_rec();
void readAndPrintReverse_rec();
void readAndPrintReverse_it(Stack *stack);


int checkCmdArg(char input[],char check[])
{
    int i,result=1;
    for(i=0; input[i]!='\0' || check[i]!='\0'; i++) {
        if(input[i] != check[i]) {
            result=0;
            break;
        }
    }
    return result;
}

/**
 * Program that reads characters from standard input
 * and then prints them in reverse order to standard output.
 *
 * If the first element in command line args equals to "rec"
 * the operation will be performed using recursion
 *
 * else the task will be performed using iteration
 */
int main(int argc, char **args) {
    if(argc > 1 && checkCmdArg(args[1],"rec"))
        run_rec();
    else
        run_it();

    return 0;
}

/**
 * Function to perform the operation by iteration.
 *
 */
void run_it(){
    printf("\n==== Performing operation by iteration ====\n");
    Stack *stack = new_stack();
    readAndPrintReverse_it(stack);
}

/**
 * Function to perform the operation by recursion
 */
void run_rec(){
    printf("==== Performing operation by recursion ====\n");
    readAndPrintReverse_rec();
}

/**
 * Function that reads one character at a time from standard input
 * and prints read characters back to standard output in reversed order.
 *
 * The function will read a character and then call itself until end of file
 * or a new line character is reached.
 *
 * The fact that the function is calling itself makes it possible to reverse the
 * characters using the call stack.
 *
 * Every time a recursive call is made, the current function call is pushed to the call stack.
 *
 * When the end of line or file is reached, the function will return to its previous caller,
 * popping it from the the call stack.
 * It will then continue after the line of its last recursive call
 * and call @code{putchar(c)} before returning to its previous caller,
 * meaning that the character that was read last will be printed first.
 *
 */
void readAndPrintReverse_rec() {
    char c = getchar();
    if (c != '\n' && c != EOF) {
        readAndPrintReverse_rec();
        putchar(c);
    }
}

/**
 * Reads characters one by one from standard input and
 * pushes them to a @code{Stack} struct.
 *
 * The stack enforces the LIFO policy
 *
 */
void readAndPrintReverse_it(Stack *stack) {
    char c = getchar();
    while (c != EOF && c != '\n') {
        stack->push(stack, c);
        c = getchar();
    }

    while (stack->isEmpty(stack) != 1) {
        char c = stack->pop(stack);
        putchar(c);
    }
}

/// ========== Implementation of stack functions ========== ///

/**
 * push a character to the top of the stack.
 */
void push(struct Stack *self, char c) {
    Node *node = createNode(c);
    node->next = self->head;
    self->head = node;
    node = NULL;
}

/**
 * pop a character from the top of the stack.
 *
 * If the list is empty, returns null termination char.
 *
 */
char pop(Stack *self) {
    if (self->head == NULL) {
        return '\0';
    } else {
        char c = self->head->data;
        self->head = self->head->next;
        return c;
    }
}

/**
 * Check if the stack is empty.
 *
 * If head @code{Node} is pointing to NULL
 * return 1
 *
 * else return 0;
 */
int isEmpty(Stack *self) {
    if (self->head == NULL)
        return 1;
    else
        return 0;
}

/**
 * Implementation of construct function used to
 * create a pointer to a @code{Stack} struct.
 *
 * allocates the memory space for the stack and initialises
 * its variables.
 *
 * @return a pointer to the created stack
 */
Stack *new_stack(){
    Stack *stack = malloc(sizeof(*stack));
    if (stack == NULL) {
        perror("malloc failed");
        exit(1);
    }
    stack->head = NULL;
    stack->push = push;
    stack->pop = pop;
    stack->isEmpty = isEmpty;
    return stack;
}
