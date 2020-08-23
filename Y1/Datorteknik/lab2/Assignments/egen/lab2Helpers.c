#include <stdio.h>
#include <lab2Helpers.h>
#define COLUMNS 6

void  print_sieves(int n) {
    int  prime[n];
    int boolean = 0;

    int i = 0;
    for(i = 2; i<n; i++){
        prime[i] = 1;                    // init array with default true values
    }
    int j=0;
    for (i = 2;i < n; i++){
        if (prime[i]){
            for (j = i;i * j < n; j++){
                prime[i * j] = 0;
            }
        }
    }

    j=0;
    int k=0;
    while(1) {
        while(k < COLUMNS){
            if(j>n){
                return;
            }
            if (prime[j] == 1) {
                printf("%10d ", j);
                k++;
            }
            j++;
        }
        k = 0;
        printf("\n");
    }
}