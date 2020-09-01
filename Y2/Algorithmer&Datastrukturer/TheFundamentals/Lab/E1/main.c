#include <stdio.h>
#include "datastructure/Stack.h"


void run();

void readAndPrintReverse_rec();

void readAndPrintReverse(Stack *stack);

void printOutput(Stack *stack);

void readStdin(Stack *stack);

/**
 * Program that reads characters from standard input
 * and then prints them in reverse order to standard output.
 *
 */
int main() {
    run();
    return 0;
}

void run() {
    printf("==== Performing operation by recursion ====\n");
    readAndPrintReverse_rec();
    printf("\n==== Performing operation by iteration ====\n");
    Stack *stack = new_stack();
    readAndPrintReverse(stack);
}

/**
 * Function that reads one character at a time from standard input
 * and prints read characters back to standard output in reversed order.
 *
 * The function will read a character and then call itself until end of file
 * or a new line character is reached.
 *
 * The fact that the function is calling itself makes it possible to reverse the
 * characters using the call stack.
 *
 * Every time a recursive call is made, the current function call is pushed to the call stack.
 *
 * When the end of line or file is reached, the function will return to its previous caller,
 * popping it from the the call stack.
 * It will then continue after the line of its last recursive call
 * and call @code{putchar(c)} before returning to its previous caller,
 * meaning that the character that was read last will be printed first.
 *
 */
void readAndPrintReverse_rec() {
    char c = getchar();
    if (c != '\n' && c != EOF) {
        readAndPrintReverse_rec();
        putchar(c);
    }
}

/**
 * Reads characters one by one from standard input and
 * pushes them to a @code{Stack} struct.
 *
 * The stack enforces the LIFO policy
 *
 */
void readAndPrintReverse(Stack *stack) {
    char c = getchar();
    while (c != EOF && c != '\n') {
        stack->push(stack, c);
        c = getchar();
    }

    while (stack->isEmpty(stack) != 1) {
        char c = stack->pop(stack);
        putchar(c);
    }
}

void printOutput(Stack *stack) {
    while (stack->isEmpty(stack) != 1) {
        char c = stack->pop(stack);
        putchar(c);
    }
}

/**
 * Iterativt
 */
void readStdin(Stack *stack) {
    char c = getchar();
    while (c != EOF && c != '\n') {
        stack->push(stack, c);
        c = getchar();
    }
}

