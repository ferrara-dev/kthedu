/*
 sieves.c
 By Samuel Ferrara.
 Lab 2 - Assignment 3
 Last modified: 2020-01-27
*/



#include <stdio.h>
#include <stdlib.h>

#define COLUMNS 6

int is_prime(int n){
	int isPrime = 1;  // antag att n är ett primtal
	if(n < 2)		 // är n < 2 så kan det inte vara ett primtal
		isPrime = 0;
	
	int i = 2;
	while(i < n-1){
			if(n%i == 0) // Om n är jämnt delbart med något godtyckligt naturligt tal i, sådant att 2 < i < n-1. Så n inte vara ett primtal
			{
				isPrime = 0;
				break;
			}
			i++;
		}
  return isPrime;
}

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

int main(int argc, char *argv[]){
  if(argc == 2)
    print_sieves(atoi(argv[1]));
  else
    printf("Please state an interger number.\n");
  return 0;
}
