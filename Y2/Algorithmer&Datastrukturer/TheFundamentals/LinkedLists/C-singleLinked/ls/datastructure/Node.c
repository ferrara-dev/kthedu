//
// Created by root on 2020-08-25.
//

#include "Node.h"

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