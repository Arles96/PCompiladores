.data
_C: .word null
_D: .word null
.text
.globl main
main:
li $t0, 1
sw $t0, _C
li $t1, 4
add $t0, $t1, 4
li $t2, 2
sub $t1, $t2, $t0
sw $t1, _D
li $v0, 1
lw $a0, _D
syscall
