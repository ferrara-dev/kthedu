//
// Created by Samuel Ferrara on 2020-08-25.
//

#include <stdio.h>
#include "Stack.h"

void print(struct Stack *self) {
    Node *n = self->head;
    while (n != NULL) {
        printf("%c |", n->data);
        n = n->next;
    }
    printf("\n");
}

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
**
 * Implementation Construct function of @code{Stack} struct.
 *
 * allocates the memory space for the stack and initialises
 * its variables.
 *
 * @return a pointer to the created node
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