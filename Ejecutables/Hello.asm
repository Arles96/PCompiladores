.data
_C: .word null
_D: .word null
.text
.globl main
main:
li $t0, 1
sw $t0, _C
li $t1, 4
div $t0, $t1, 2
sw $t0, _D
sw $t0, _D
li $v0, 1
lw $a0, _D
syscall
