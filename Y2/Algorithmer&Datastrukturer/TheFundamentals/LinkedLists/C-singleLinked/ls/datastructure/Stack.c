//
// Created by Samuel Ferrara on 2020-08-25.
//

#include "Stack.h"
#include <stdio.h>

void print(struct Stack *self) {
    Node *n = self->head;
    while (n != NULL) {
        printf("%c |", n->data);
        n = n->next;
    }
    printf("\n");
}

/**
 * push a character to the top
 * of the stack.
 */
void push(struct Stack *self, char c) {
    Node *node = createNode(c);
    node->next = self->head;
    self->head = node;
    node = NULL;
}

/**
 * pop a character from the top
 * of the stack.
 */
char pop(Stack *self) {
    if (self->head == NULL) {
        exit(1);
    } else {
        char c = self->head->data;
        self->head = self->head->next;
        return c;
    }
}

int isEmpty(Stack *self) {
    if (self->head == NULL)
        return 1;
    else
        return 0;
}

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