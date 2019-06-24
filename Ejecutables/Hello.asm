.data
_x: .word 1
.text
.globl main
main:
li $v0, 5
syscall
sw $v0, x
