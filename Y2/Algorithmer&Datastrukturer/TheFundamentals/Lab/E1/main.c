#include <stdio.h>
#include "datastructure/Stack.h"

void run_it();
void run_rec();
void readAndPrintReverse_rec();
void readAndPrintReverse_it(Stack *stack);


int checkCmdArg(char input[],char check[])
{
    int i,result=1;
    for(i=0; input[i]!='\0' || check[i]!='\0'; i++) {
        if(input[i] != check[i]) {
            result=0;
            break;
        }
    }
    return result;
}

/**
 * Program that reads characters from standard input
 * and then prints them in reverse order to standard output.
 *
 * If the first element in command line args equals to "rec"
 * the operation will be performed using recursion
 *
 * else the task will be performed using iteration
 */
int main(int argc, char **args) {
    if(argc > 1 && checkCmdArg(args[1],"rec"))
        run_rec();
    else
        run_it();

    return 0;
}

/**
 * Function to perform the operation by iteration.
 *
 */
void run_it(){
    printf("\n==== Performing operation by iteration ====\n");
    Stack *stack = new_stack();
    readAndPrintReverse_it(stack);
}

/**
 * Function to perform the operation by recursion
 */
void run_rec(){
    printf("==== Performing operation by recursion ====\n");
    readAndPrintReverse_rec();
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
void readAndPrintReverse_it(Stack *stack) {
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

