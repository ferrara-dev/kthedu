#include <stdio.h>
#include "Stack.h"
void readAndPrintReversed_Rec();

int main() {
    readAndPrintReversed_Rec();
    return 0;
}

void readAndPrintReversed_Rec() {
    char c = getchar();
    if (c != EOF && c != '\n') {
        readAndPrintReversed_Rec();
        printf("%c", c);
    }
}


/**
 * Iteration
 *
 */
void readAndPrintReverse(Stack *stack){
    char c = getchar();

    while (c != EOF && c != '\n') {
        stack->push(stack,c);
        c = getchar();
    }

    while (stack->isEmpty(stack) != 1) {
        char c = stack->pop(stack);
        putchar(c);
    }
}