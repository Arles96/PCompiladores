.data
_I: .word 0
_X: .word 0
.text
.globl main
main:
li $t0, 1
sw $t0, _I
lw $t0, _I
lw $t1, _I
beq $t0, $t1, _etiq2
li $t0, 0
b _etiq3
_etiq2:
li $t0, 1
_etiq3:
lw $t1, _I
lw $t2, _I
blt $t1, $t2, _etiq4
li $t1, 0
b _etiq5
_etiq4:
li $t1, 1
_etiq5:
and $t2, $t0, $t1
beq $t2, 1, _etiq0
b _etiq1
_etiq0:
li $t0, 4
sw $t0, _X
_etiq1:
