#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int factlist[8];
int fact(int n){
int r = 1;
while(n > 0){
r = r * n;
n--;
}
return r;
}
void makelist(int start, int length){
for(int i=0; i<length; i++){
factlist[i] = fact(start);
start++;
}
}
int main(){
makelist(3,sizeof(factlist)/sizeof(int));
return 0;
}
