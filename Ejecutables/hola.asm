.data
mgs0: .asciiz "Fin del programa \n"
_I: .word 0
_X: .word 0
.text
.globl main
main:
li $t0, 1
sw $t0, _I
li $t0, 4
sw $t0, _X
_etiq0:
lw $t0, _I
li $t1, 6
ble $t0, $t1, _etiq3
li $t0, 0
b _etiq4
_etiq3:
li $t0, 1
_etiq4:
lw $t1, _X
li $t2, 4
beq $t1, $t2, _etiq5
li $t1, 0
b _etiq6
_etiq5:
li $t1, 1
_etiq6:
and $t2, $t0, $t1
beq $t2, 1, _etiq1
b _etiq2
_etiq1:
li $v0, 1
lw $a0, _I
syscall
lw $t0, _I
add $t1, $t0, 1
sw $t1, _I
b _etiq0
_etiq2:
li $t1, 4
add $t0, $t1, 4
sw $t0, _X
li $v0, 4
la $a0, mgs0
syscall
