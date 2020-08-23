.data
	number1: .word 6
	number2: .word 8
	sum:     .word 0
.text
	main:
		lw $t0,number1($zero)
		lw $t1,number2($zero)

		add $t2,$t1,$t0
		li $v0,1
		add $a0,$0,$t2
		syscall 
		
		add $t0,$t0,$t1
		