//
// Created by root on 2020-08-25.
//

#ifndef LS_QUEUE_H
#define LS_QUEUE_H
#endif //LS_QUEUE_H

#include "Node.h"
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
