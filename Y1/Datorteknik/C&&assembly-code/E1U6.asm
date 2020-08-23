# Assume that store byte, store word, load byte, and load word all take the same number of
# clock cycles. Create a new memory copy function that is more efficient than the previous
# function.

.data
	sourceAdress: .word 0x7fffffff,0x6fffffff,0x5fffffff
	.space 20 # Reserve next 20 bites words in this adress line 
	destinationAdress: .space 32 # Reserve the next adressline as the destination adress
.text
	main:
	la $a0,sourceAdress		# load sourceadress to register a0
	la $a1,destinationAdress # load destination adress to register a1
	li $a2,3 		   		# copy 4 words 
	
	jal copyWord
	
	stop: j stop
	
	copyWord:
	loop:
	beq $a2,$0,done 
	lw $t0, 0($a0) # load word at index a0 from sourceadress to t0 
	sw $t0, 0($a1) # save the word to destinationadress at index a1
	addi $a0, $a0, 4 # increment the pointer 4 bytes ahead --> to the next word in sourceadress
	addi $a1, $a1, 4 # increment the pointer 4 bytes ahead --> to the next word in sourceadress
	addi $a2, $a2, -1 # Decrement the counter
	
	j loop
	done:
	jr $ra