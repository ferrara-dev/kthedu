.data
	factList: .word 0,0,0,0,0,0,0,0 # Declare an array with space for 8 integers
		
.text
main:
	la $a2, factList
	li $a0,8 # [int length] --> length of the array 0x8
	li $a1,3 # [int start] --> 0x3 
	jal makelist
	nop
	
makelist:
	addi $t0, $0, 0
	add $t1, $0, $a1
	addi $a3, $0, 3
	sw $t1, 32($a2) ## remove?
	for:    # for-loop
		beq $t0,$a0,doneFor
		jal fact
		addi $t0, $t0, 1
		addi $t1, $t1, 1
		sw $v1,0($a2)
		addi $a2, $a2,  4
		lw $v1,0($a2)
		addi $a1, $a1, 1
		j for
		
	doneFor:
		addi $t0, $0, 0
		addi $t1, $0, 0
		stop: j stop
		nop
		
fact:
	addi $t3, $0, 1
	add $t2, $0, $a1
	addi $t6, $0, 1

	for2:    #while-loop
		ble $t2,$0,doneWhile
		mul $t3, $t2, $t3
		move $v1,$t3
		#add $v1, $v1, $t7 # function return value v0 = a0+a1 
		addi $t2, $t2, -1
	
	j for2
		nop
	doneWhile:
		jr $ra
		nop
	