#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int factlist[10];
int fact(int n) {
	int r = 1;
	while(n > 0) {
		r = r * n;
		n--;
	}
	return r;
}
void makelist(int start, int length) {
	int i;
	for(i=0; i<length; i++) {
		factlist[i] = fact(start);
		start++;
	}
}
int main() {

	printf("%d\n ------- \n ", sizeof(factlist));
	
	makelist(3,sizeof(factlist)/sizeof(int));
	int k = 0;
	  for (k = 0; k < sizeof(factlist)/sizeof(int); k++)
            printf( "%d\n", factlist[k]) ;
	return 0;
}
