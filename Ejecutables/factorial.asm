.data
mgs0: .asciiz "Ingrese un numero "
mgs1: .asciiz "\nEl resultado es "
_i: .word 0
_x: .word 0
_y: .word 0
.text
.globl main
main:
li $v0, 4
la $a0, mgs0
syscall
li $v0, 5
syscall
sw $v0, _x
li $t0, 1
sw $t0, _y
li $t0, 1
sw $t0, _i
_etiq0:
lw $t0, _i
lw $t1, _x
ble $t0, $t1, _etiq1
b _etiq2
_etiq1:
lw $t0, _y
lw $t1, _i
mul $t2, $t0, $t1
sw $t2, _y
lw $t0, _i
add $t1, $t0, 1
sw $t1, _i
b _etiq0
_etiq2:
li $v0, 4
la $a0, mgs1
syscall
li $v0, 1
lw $a0, _y
syscall
