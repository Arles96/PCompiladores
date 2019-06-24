.data
_x: .word 11.0
.text
.globl main
li $t0, 0
sw $t0, _y
li $t0, 0
sw $t0, _yy
li $t0, 12
sw $t0, _az
li $t0, 1
sw $t0, _yy
main:
li $t0, 1
sw $t0, _x
