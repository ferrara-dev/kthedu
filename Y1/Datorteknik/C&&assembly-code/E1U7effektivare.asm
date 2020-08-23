
# Definierar instruktionen PUSH
# Används för att spara adress til stacken

.macro PUSH(%reg)
	addi $sp,$sp,-4  # flytta pekaren till nästa adress på stacken
	sw %reg,0($sp)   # Spara adressen på den nya positionen
.end_macro
# Definierar instruktionen POP
# används för att ladda upp sparad adress från stacken
.macro POP (%reg)
	lw %reg,0($sp) # Ladda upp adressen från den befintliga positionen
	addi $sp,$sp,4 # flytta pekaren till föregående adress i stacken
.end_macro

.data
	.align 2
	factList: .space 32# skapar en array med plats för 8 integers
	
.text
	start:
		jal main
		stop: j stop	
		nop
		
		# Factorial funciton n!
		# Input: $a0 = value n
		# Output: $v0 = the new value n!
		
		
		main: PUSH ($ra) # addi $sp,$sp,-4 
					 # sw %reg,0($sp) 
		addi $a0,$0,3 # start = 3
		addi $a1,$0,8 # length = 8d
		jal makelist
		POP ($ra)
		jr $ra
		nop
	fact: addi $v0,$0,1 # r = 1
		factloop:
			ble $a0,$0,donefact # n <= 0
			mul $v0,$v0,$a0 # r = r * n
			addi $a0,$a0,-1 # n--
			j factloop
		donefact:
			jr $ra	
	# Creates a list of factorial numbers
# Input: $a0 = start address
# $a1 = lenght in bytes
		makelist:
		PUSH ($ra)  # addi $sp,$sp,-4  # flytta pekaren till nästa adress på stacken
					# sw $ra,0($sp)   # Spara adressen på den nya positionen
					
		PUSH ($s0) # for start
		PUSH ($s1) # for length
		PUSH ($s2) # for i
		PUSH ($s3) # for factlist address
		
		move $s0,$a0 # save start
		move $s1,$a1 # save length
		addi $s2,$0,0 # i = 0
		la $s3,factList # ladda upp adressen till factlist
		makeloop:
			slt $t0,$s2,$s1 # i < length
			beq $t0,$0,makeend # jump if end of while
			move $a0,$s0 # setup argument
			jal fact # call fact
			sll $t0,$s2,2 # get correct word address
			add $t1,$s3,$t0 # adds address & i counter
			sw $v0,0($t1) # store the result

			addi $s0,$s0,1 # start++
			addi $s2,$s2,1 # i++
		j makeloop
		makeend:
			POP ($s3)
			POP ($s2)
			POP ($s1)
			POP ($s0)
			POP ($ra)
			jr $ra
			