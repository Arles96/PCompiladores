.data
_I: .word 0
_X: .word 0
.text
.globl main
main:
li $t0, 1
sw $t0, _I
li $t0, 0
sw $t0, _t0
_etiq2:
li $t0, 1
sw $t0, _t0
_etiq3:
li $t0, 0
sw $t0, _t1
_etiq4:
li $t0, 1
sw $t0, _t1
_etiq5:
_etiq0:
li $t0, 4
sw $t0, _X
_etiq1:
