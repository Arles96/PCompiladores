.data
_I: .word 0
_X: .word 0
.text
.globl main
main:
li $t0, 1
sw $t0, _I
li $t0, 1
sw $t0, _I
li $t0, 5
_etiq0:
lw $t1, _I
lw $t2, _I
ble $t1, $t2, _etiq1
b _etiq2
_etiq1:
li $t1, 4
sw $t1, _X
lw $t1, _I
add $t2, $t1, 1
b _etiq0
_etiq2:
