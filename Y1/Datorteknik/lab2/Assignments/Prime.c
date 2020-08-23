/*
 prime.c
 By David Broman.
 Last modified: 2015-09-15
 This file is in the public domain.
*/


#include <stdio.h>
/*
  Ett primtal är endast delbart med sig själv och 1
  Om något tal n är delbart med något annat heltal tal i intervallet
  [2,n-1] så är det inte 
*/
int is_prime(int n){
	int isPrime = 1;  // antag att n är ett primtal
	if(n < 1)		 // är n < 1 så kan det inte vara ett primtal
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

int main(void){
  printf("%d\n", is_prime(11));  // 11 is a prime.      Should print 1.
  printf("%d\n", is_prime(383)); // 383 is a prime.     Should print 1.
  printf("%d\n", is_prime(987)); // 987 is not a prime. Should print 0.
}
