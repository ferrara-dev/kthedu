/*
 sieves.c
 By Samuel Ferrara.
 Lab 2 - Assignment 3
 Last modified: 2020-01-27
*/



#include <stdio.h>
#include <stdlib.h>
#include <lab2Helpers.h>
void print_sieves(int n);

        int main(int argc, char *argv[]){
    if(argc == 2)
        print_sieves(atoi(argv[1]));
    else
        printf("Please state an interger number.\n");
    return 0;
}