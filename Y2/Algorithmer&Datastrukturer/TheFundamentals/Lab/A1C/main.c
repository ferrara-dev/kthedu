#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
 * Check if a string has matching parentheses.
 * If the parentheses match, returns a pointer
 */
/* Search a string for matching parentheses.
 * If the parentheses match, returns a pointer that addresses the nul terminator at the end of the string.
 * If they don't match, the pointer addresses the first character that doesn't match.
 */
const char *match(const char *str) {
    if (*str == '\0' || *str == ')') {
        return str;
    }
    if (*str == '(') {
        const char *closer = match(++str);
        if (*closer == ')') {
            return match(++closer);
        }
        return str - 1;
    }

    return match(++str);
}


char *getTestData();
void test(char *testData);

int main() {
    char *data;
    int run = 1;
    while (run == 1){
        data = getTestData();
        if(*data == EOF){
            run = 0;
            continue;
        }
        test(data);
    }
    return 0;
}

void test(char *data){
    printf("Test data : ");
    for (int i = 0; i < sizeof(data) / sizeof(data[0]); i++)
        printf("%c", data[i]);

    const char *result = match(data);
    if ((*result) == '\0')
        printf("\n Parentheses are balanced !\n");
    else
        printf("\nParentheses are NOT balanced \n");

}
char *getTestData() {
    char *characters = malloc(sizeof(*characters));
    char c = getchar();
    int count = 0;
    while (c!= EOF && c != '\n') {
        *(characters + count) = c;
        count++;
        c = getchar();
    }

    if(count == 0)
        *(characters) = EOF;
    return characters;
}
