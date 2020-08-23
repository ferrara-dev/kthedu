/*
 sieves.c
 By Samuel Ferrara.
 Lab 2 - Assignment 3
 Last modified: 2020-01-27
*/



#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define COLUMNS 6



void  print_sieves(int n) {
	int  *prime = malloc(sizeof(int)*n); // allocate space for n integers in the heap
    int boolean = 0;
	
	int i = 2;
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
				free(prime);
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

int main(int argc, char *argv[]){

  if(argc == 2)
    print_sieves(atoi(argv[1]));
  else
    printf("Please state an interger number.\n");
  return 0;
}
