#! /bin/bash

rm *.class
rm Ada95.java
rm parser.java
rm sym.java

jflex ada95.flex

java -cp .:java-cup-11b.jar java_cup.Main < parser.cup
javac -cp .:java-cup-11b.jar Main.java
echo ""
echo "Mostrando Resultado"
echo ""
java -cp .:java-cup-11b.jar Main $1
