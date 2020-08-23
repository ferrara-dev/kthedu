main:
addi $a0, $0, 3 #Argument x = 3
addi $a1, $0, 8 #Argumet y = 8
jal sum 	# Jump and link to label "sum"
add $s0, $v0, $0

sum:
add $v0,$a0,$a1
jr $ra