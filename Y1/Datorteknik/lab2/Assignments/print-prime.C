/*
 print-prime.c
 By David Broman.
 Last modified: 2015-09-15
 This file is in the public domain.
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

void print_primes(int n){
  // Should print out all prime numbers less than 'n'
  // with the following formatting. Note that
  // the number of columns is stated in the define
  // COLUMNS
  int k = 0;
  int check = 0;
  int number = 0;
  while(1) {
	  k = 0;
	  while(k < COLUMNS){
		  if(number > n){
			return;
		}
		  check = is_prime(number);
		  if(check) {
			printf("%10d ", number);
			k++;
		  }
		number++;
		
	  }
	  printf("\n");
  }
}


// 'argc' contains the number of program arguments, and
// 'argv' is an array of char pointers, where each
// char pointer points to a null-terminated string.
int main(int argc, char *argv[]){
  if(argc == 2)
    print_primes(atoi(argv[1]));
  else
    printf("Please state an interger number.\n");
  return 0;
}

 
