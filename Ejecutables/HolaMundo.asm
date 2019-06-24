.data
mgs0: .asciiz "Hola Mundo"
.text
.globl main
main:
li $v0, 4
la $a0, mgs0
syscall
