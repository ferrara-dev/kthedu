.data
	newLine: .asciiz "\n"
.text
	
	main:
	addi $s0,$0,10
	
	# print value of s0
	li $v0,1
	move $a0,$s0
	syscall
	
	# print new line
	li $v0,4
	la $a0, newLine
	syscall 
	
	# jump to label
	jal increaseMyRegister
	
	
	
	# signal end of program
	li $v0,10
	syscall
	
	increaseMyRegister:
	addi $sp, $sp, -4 # alocate space for 1 element
	sw $s0,0($sp) # save the value in s0 to the first location of the stack pointer
	
	addi $s0, $s0, 5 # s0 = s0 + 5
	li $v0,1		  # print the result
	move $a0,$s0
	syscall
	
	li $v0,4
	la $a0, newLine
	syscall 
	
	sll $s0, $s0, 2        # s0 = s0 * 4
	li $v0,1		  # print the new value of s0
	move $a0,$s0
	syscall
	
	li $v0,4
	la $a0, newLine
	syscall 
	
	lw $s0, 0($sp)
	addi $sp, $sp, 4
	
	# print value of s0 one more time
	li $v0,1
	move $a0,$s0
	syscall
	
	jr $ra
	
	