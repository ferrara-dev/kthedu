.data
	message: .asciiz "hello, we are going to do som calculations \n"
.text

	main:
	jal displayMessage  # (1) jump to label "displayMessage"
					# (3) continue with remaing instructions
	
					
	addi $a0, $0, 10   	## function arguments
	addi $a1, $0, 5		
	
	jal addNumbers 	# (4) jump to label "addNumbers"
	
	li $v0, 10
	syscall 
	
			
	displayMessage:   #  (2) execute instructions
	li $v0,4		   #  (2.1) prepare system to write string to console
	la $a0, message
	syscall		   # (2.3) printout that the message
	
	jr $ra		   # (2.4) return to main
	
	addNumbers:
	add $v1, $a0, $a1 # function return value v0 = a0+a1 
	li $v0,1	   #  (2.1) prepare system to write string to console
	addi $a0, $v1,0
	syscall
	jr $ra		   # (2.4) return to main
	
	
	
	
	
	
