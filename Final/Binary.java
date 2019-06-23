package Final;

import java.util.LinkedList;

import Intermedio.Mips;
import Intermedio.RowMip;
import Intermedio.TokenMip;

public class Binary {

  public LinkedList<String> lineCode = new LinkedList<String>();
  private Mips mips;
  private boolean initialVars = true;

  // constructores

  public Binary () {}

  public Binary (Mips mips) {
    this.mips = mips;
  }

  // getters and setters

  public void setMips (Mips mips) {
    this.mips = mips;
  }

  public Mips getMips () {
    return mips;
  }

  // funcion para agregar lineas de codigo
  private void addLine (String line) {
    lineCode.add(line);
  }

  // funcion para la impresión de codigo
  private void generateCodePut (RowMip line) {
    addLine("li $v0, 4");
    addLine("la $a0, _" + line.getResult());
    addLine("syscall");
  }

  public void generateCode () {
    addLine(".data");
    // generando las declaraciones de los strings
    for (RowMip line : mips.codeString) {
      String code = "_" + line.getResult() + ": .asciiz " + line.getValue1();
      addLine(code);
    }
    // generando todo el codigo
    for (RowMip line : mips.code) {
      // codigo de variables globales o iniciales
      if (line.getToken().equals(TokenMip.ASSIGN) && initialVars == true) { // generando variables globales
        addLine("_" + line.getResult() + ": .word " + line.getValue1());
      } else if (!line.getToken().equals(TokenMip.ASSIGN) && initialVars == true) { // generando el codigo administrativo
        initialVars = false;
        String code1 = ".text";
        addLine(code1);
        String code2 = ".globl main";
        addLine(code2);
      }
      // generacion de etiquetas
      if (line.getToken().equals(TokenMip.ETIQ)) {
        if (line.getResult().equals(TokenMip.MAIN)) {
          String code = line.getResult() + ":";
          addLine(code);
        } else {
          String code = "_" + line.getResult() + ":";
          addLine(code);
        }
      }
      // generacion de codigo para el token put
      if (line.getToken().equals(TokenMip.PUT)) {
        generateCodePut(line);
      }
    }
  }

  public void saveCodeFile () {

  }

  public void printCode () {
    for (String line : lineCode) {
      System.out.println(line);
    }
  }

}