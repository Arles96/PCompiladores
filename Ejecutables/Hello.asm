.data
_C: .word 0
_A: .word FALSE
_B: .word FALSE
.text
.globl main
main:
li $t0, 0
sw $t0, _Y
add $t0, 4, 4
add $t1, 5, $t0
sw $t1, _X
