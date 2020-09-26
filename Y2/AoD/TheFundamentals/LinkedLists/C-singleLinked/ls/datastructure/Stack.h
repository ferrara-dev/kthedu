//
// Created by root on 2020-08-25.
//

#ifndef LS_QUEUE_H
#define LS_QUEUE_H
#endif //LS_QUEUE_H

#define POLICY "FIFO"
#include "Node.h"
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
