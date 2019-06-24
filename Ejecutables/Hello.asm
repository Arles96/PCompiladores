.data
_C: .asciiz "Hi"
_D: .asciiz "Hola"
.text
.globl main
main:
li $t0, 1
li $t1, 1
and $t3, $t0, $t1
beq		$t3, 1, etiq1	# if $t3 == 1 then etiq1
b		etiq2	 # branch to etiq2
etiq1:
  li $v0, 4
  la $a0, _C
  syscall
etiq2:
  li $v0, 4
  la $a0, _D
  syscall