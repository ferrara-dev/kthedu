//
// Created by root on 2020-09-01.
//

#ifndef E11_NODE_H
#define E11_NODE_H

#include <stdlib.h>
typedef struct Node Node;

struct Node{
    char data;
    struct Node *next;
};
Node * createNode(char data);

#endif //E11_NODE_H
