
addi $a0, $zero,3
twinkle:
	addi $t1,$zero,0
	addi $t2,$zero,1
	
	while:
		slt $at, $zero,$a0
		beq $at, $zero,end
		nop
		
		else:
		xori $t1,$t1,0x0100
		addi $a0,$a0,-1
		
		j while
		nop
	end:
		j end
		nop
		
