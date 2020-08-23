  # hexmain.asm
  # Written 2015-09-04 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

	.text
main:
	li	$a0,17		# change this to test different values

	jal	hexasc		# call hexasc
	nop			# delay slot filler (just in case)	

	move	$a0,$v0		# copy return value to argument register

	li	$v0,11		# syscall with v0 = 11 will print out
	syscall			# one byte from a0 to the Run I/O window
	
stop:	j	stop		# stop after one run
	nop			# delay slot filler (just in case)

  # You can write your own code for hexasc here
  #
  
hexasc:
	andi $a0,$a0,0x0F
	addi $t0,$0,10
	# if($a0 <= 9)
		## add 48 to a0
	slt $t1,$t0,$a0 
	beq $t1,$0,nineOrLess
	# else
		## add 55 to a0
	addi $v0,$a0,0x41 
	
	return: jr $ra
	nop

	nineOrLess:
		addi $v0,$a0,0x30
		j return
		nop
		