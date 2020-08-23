

#include <stdio.h>

char* text1 = "This is a string.";
char* text2 = "Yet another thing.";

int list1[80];
int list2[80];

int count = 0;
/*
Task: The file pointers.asm contains an assembler program with two functions: work and
copycodes. Your task is to translate these two assembly functions into C functions. Note that the
data declarations for text1 and text2 are already provided. You also need to declare list1,
list2, and count correctly in the C code. Your C functions must implement pointers and
arguments correctly, including correct handling of types. You may not use array indexing in this
exercise.
Note: your two C functions (work and copycodes) have to be interchangeable with the assembly
language versions. Parameters and results must match, so that the code to call your function is the
same as the code to call the corresponding assembly-language function.
If your program is implemented correctly, the following should be printed when executing the
program pointers.c:

list1: ASCII codes and corresponding characters.
0x054 'T' 0x068 'h' 0x069 'i' 0x073 's' 0x020 ' ' 0x069 'i' 0x073 's'
0x020 ' ' 0x061 'a' 0x020 ' ' 0x073 's' 0x074 't' 0x072 'r' 0x069 'i'
0x06E 'n' 0x067 'g' 0x02E '.'
list2: ASCII codes and corresponding characters.
0x059 'Y' 0x065 'e' 0x074 't' 0x020 ' ' 0x061 'a' 0x06E 'n' 0x06F 'o'
0x074 't' 0x068 'h' 0x065 'e' 0x072 'r' 0x020 ' ' 0x074 't' 0x068 'h'
0x069 'i' 0x06E 'n' 0x067 'g' 0x02E '.'
Count = 35
Endian experiment: 0x23,0x00,0x00,0x00
*/


// # function copycodes()
void copycodes(char *text, int *list, int *count){
	volatile int* charAdress = (volatile int*) (text+0);
	volatile int* listAdress = (volatile int*) (list+0);
	int countTemp = 0;
	char t0 = *(charAdress);
	//int t1 = count;
	int j = 0;
	while( t0 != (char) 0){
		countTemp = j;
		charAdress = (volatile int*) (text+j);
		listAdress = (volatile int*) (list+j);
		t0 = *(charAdress);
		*listAdress = t0;
		printf("'%c' \n", t0);
		j++;
	}
	*count = *count + countTemp;
	
	
/*	
loop:
	lb	$t0,0($a0)	
	beq	$t0,$0,done
	sw	$t0,0($a1)

	addi	$a0,$a0,1
	addi	$a1,$a1,4
	
	lw	$t1,0($a2)
	addi	$t1,$t1,1
	sw	$t1,0($a2)
	j	loop
done:
	jr	$ra
*/
}		

void work(){
		copycodes(text1,(int*) &list1, &count);
		copycodes(text2,(int*) &list2, &count);
	/*
		copycodes(text2,list2,count);
	PUSH	($ra)
	la 	$a0,text1
	la	$a1,list1
	la	$a2,count
	jal	copycodes
	
	la 	$a0,text2
	la	$a1,list2
	la	$a2,count
	jal	copycodes
	POP	($ra)
	*/
}
void printlist(const int* lst){
  printf("ASCII codes and corresponding characters.\n");
  while(*lst != 0){
    printf("0x%03X '%c' ", *lst, (char)*lst);
    lst++;
  }
  printf("\n");
}

void endian_proof(const char* c){
  printf("\nEndian experiment: 0x%02x,0x%02x,0x%02x,0x%02x\n", 
         (int)*c,(int)*(c+1), (int)*(c+2), (int)*(c+3));
  
}

int main(void){
  work();

  printf("\nlist1: ");
	printlist(list1);
  printf("\nlist2: ");
	printlist(list2);
  printf("\nCount = %d\n", count);

  endian_proof((char*) &count);
}