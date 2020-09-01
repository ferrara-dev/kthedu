//
// Created by root on 2020-09-01.
//

#ifndef E11_STACK_H
#define E11_STACK_H

#include "Node.h"
/**
 * Declaration of typedef @code{Stack} struct.
 *
 * The stack holds a @{Node} pointer, pointing
 * to the element at the top of the stack.
 *
 * It also holds function pointers to the methods that it uses
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

#endif //E11_STACK_H
