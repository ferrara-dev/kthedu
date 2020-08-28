#include <stdio.h>
#include "datastructure/Stack.h"

void run(const char *commandlineArg);

void readAndPrintReverse_rec();

void readAndPrintReverse(Stack *stack);

void printOutput(Stack *stack);

void printOutput_rec(Stack *stack);

void readStdin(Stack *stack);

void readStdin_rec(Stack *stack);

/**
 * Program that reads characters from standard input
 * and then prints them in reverse order to standard output.
 *
 * If the first command line argument equals "rec" the operation
 * will be performed recursively.
 */
int main(int argc, char *argv[]) {
    run(argv[1]);
    return 0;
}


void run(const char *commandLineArg) {
    Stack *stack = new_stack();
    if (commandLineArg == 'r')
        readAndPrintReverse_rec();
    else
        readAndPrintReverse(stack);
}

/***
 * Take chars from standard input and write them
 * in reverse order to standard output.
 *
 * Characters are stored on the execution stack.
 * New instances of the variable are created on
 * the stack each time the function is entered.
 *
 * When EOF is encountered, the call chain will be ended and
 * readAndPrintReverse_rec will return without calling itself again.
 *
 * This will in turns make all callers return and call @code{putchar(c)} on the next line.
 *
 * The callers will return one by one in LIFO order, meaning that the characters that has been
 * read from stdin will be writen to stdout in reverse order.
 */
void readAndPrintReverse_rec() {
    printf("Operation performed with recursion \n");
    char c = getchar();
    if (c != EOF) {
        readAndPrintReverse_rec();
        putchar(c);
    }
}

void readAndPrintReverse(Stack *stack) {
    readStdin(stack);
    printOutput(stack);
}

/**
 * Iterativt
 */
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

/**
 * Read characters from standard input
 * and store them in a list with FIFO-policy
 * with recursion.
 */
void readStdin_rec(Stack *stack) {
    char c = getchar();
    if (c == EOF | c == '\n')
        return;

    stack->push(stack, c);

    readStdin_rec(stack);
}

/**
 * Write characters to standard output
 * with recursion.
 */
void printOutput_rec(Stack *stack) {
    if (stack->isEmpty(stack))
        return;
    char c = pop(stack);
    putchar(c);
    printOutput_rec(stack);
}


