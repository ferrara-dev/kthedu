//
// Created by root on 2020-08-25.
//

#ifndef LS_NODE_H
#define LS_NODE_H
#include <stdlib.h>
typedef struct Node Node;

struct Node{
    char data;
    struct Node *next;
};

Node * createNode(char data);

#endif //LS_NODE_H
