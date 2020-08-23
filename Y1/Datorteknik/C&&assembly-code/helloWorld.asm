.data 
	myMessage: .asciiz "hello"
.text
	li $v0, 4
	la $a0, myMessage
	syscall