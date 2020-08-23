# Construct a MIPS assembly function that copies data between two memory addresses.
# The first function parameter is the source address, the second parameter the destination
# address, and the third parameter the size of the data that should be copied (in bytes).
# The function must copy one byte each time, that is, use the load byte and the store byte
# instructions. The function must also follow MIPS register calling conventions.

.data
	# Create an array with 3 integer values in the memory
	sourceAdress: .word 0x7fffffff,0x6fffffff,0x5fffffff
	.space 20 # Reserve next 20 bites words in this adress line 
	destinationAdress: .space 32 # Reserve the next adressline as the destination adress
.text
	main:
	#------------------Declaration of function arguments--------------------#
	#														  #
	# a0 = Memory adress of the data that is to be copied 				  #
	# a1 = Memory Adress of the reserved space, where the data is copied to #
	#-----------------------------------------------------------------------#
	la $a0, sourceAdress ## load the source adress to register a0
	la $a1, destinationAdress # load the destination adress to register a1 
	li $a2,12 # copy 16 bytes (4 words) [ int i = 16 ]
	jal copy
	
	stop: j stop
	
	copy:
	
	loop:						# loop equvialent to --> [ for(i = 16; i!=0; i--) ]
	beq $a2,$zero,done # Branch to label stop if a2 == 0
	lb $t0,0($a0) # load byte at position 0 relative to a0
	sb $t0,0($a1) # store byte at position 0 relative to a1
	addi $a0,$a0,1 # increment src pointer
	addi $a1,$a1,1 # increment dst pointer
	addi $a2,$a2,-1 # decrement counter [int i --]
	j loop
	
	done:
		jr $ra
	