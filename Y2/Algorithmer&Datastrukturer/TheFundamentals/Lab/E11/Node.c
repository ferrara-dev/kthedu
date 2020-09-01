//
// Created by root on 2020-09-01.
//
#include "Node.h"
#include <stdlib.h>

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