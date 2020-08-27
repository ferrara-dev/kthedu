#include <stdio.h>
#include "datastructure/Stack.h"

void run(const char* commandlineArg);
void readAndPrintReverse_rec(Stack *stack);
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

void run(const char* commandLineArg){
    Stack *stack = new_stack();
    if(commandLineArg == "rec")
        readAndPrintReverse_rec(stack);
    else
        readAndPrintReverse(stack);
}

void readAndPrintReverse_rec(Stack *stack){
    printf("Operation performed with recursion \n");
    readStdin_rec(stack);
    printOutput_rec(stack);
}

void readAndPrintReverse(Stack *stack){
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
        stack->push(stack,c);
        c = getchar();
    }
}

/**
 * Read characters from standard input
 * and store them in a list with FIFO-policy
 * with recursion.
 */
void readStdin_rec(Stack *stack)
{
    char c = getchar();
    if (c == EOF | c=='\n')
        return;

    stack->push(stack,c);

    readStdin_rec(stack);
}

/**
 * Write characters to standard output
 * with recursion.
 */
void printOutput_rec(Stack *stack){
    if(stack->isEmpty(stack))
        return;
    char c = pop(stack);
    putchar(c);
    printOutput_rec(stack);
}


