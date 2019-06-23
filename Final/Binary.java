package Final;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import Intermedio.Mips;
import Intermedio.RowMip;
import Intermedio.TokenMip;

public class Binary {

  public LinkedList<String> lineCode = new LinkedList<String>();
  public LinkedList<TempVar> $t = new LinkedList<TempVar>();
  public LinkedList<TempVar> $a = new LinkedList<TempVar>();
  public LinkedList<TempVar> $s = new LinkedList<TempVar>();

  private int limitT = 8;
  private int limitA = 4;
  private int limitS = 8;
  private Mips mips;
  private boolean initialVars = true;

  // constructores

  public Binary () {
    for (int i = 0; i < limitT; i++) {
      $t.add(new TempVar("$t" + i));
    }
    // temporal $a
    for (int i = 0; i < limitA; i++) {
      $a.add(new TempVar("$a" + i));
    }
    // temporal $s
    for (int i = 0; i < limitS; i++) {
      $s.add(new TempVar("$s" + i));
    }
  }

  public Binary (Mips mips) {
    this.mips = mips;
    // temporals $t
    for (int i = 0; i < limitT; i++) {
      $t.add(new TempVar("$t" + i));
    }
    // temporal $a
    for (int i = 0; i < limitA; i++) {
      $a.add(new TempVar("$a" + i));
    }
    // temporal $s
    for (int i = 0; i < limitS; i++) {
      $s.add(new TempVar("$s" + i));
    }
  }

  // getters and setters

  public void setMips (Mips mips) {
    this.mips = mips;
  }

  public Mips getMips () {
    return mips;
  }

  // funcion para llenar una variable temporal
  public TempVar addVar (String temp) {
    for (TempVar var : $t) {
      if (var.getEmpty()) {
        var.addVar(temp);
        return var;
      }
    }
    return null;
  }

  // funcion para obtener la variable temporal
  public TempVar getVar (String temp) {
    for (TempVar var : $t) {
      if (var.getVar().equals(temp)) {
        return var;
      }
    }
    return null;
  }

  // funcion para limpiar una variable temporal
  public boolean clearVar (String temp) {
    for (TempVar var : $t) {
      if (var.getVar().equals(temp)) {
        var.clearVar();
        return true;
      }
    }
    return false;
  }

  // funcion para agregar lineas de codigo
  private void addLine (String line) {
    lineCode.add(line);
  }

  // funcion para generar codigo final en asignar
  public void generaCodeAssing (RowMip line) {
    if (initialVars == true) { // generando variables globales
      addLine("_" + line.getResult() + ": .word " + line.getValue1());
    } else {
      String value = line.getValue1();
      if (line.getValue2() == null && value.charAt(0) != 't') { //  si es asignacion
        TempVar var = addVar(line.getResult());
        addLine("li " + var.getTemp() + ", " + value);
        addLine("sw " + var.getTemp() + ", _" + line.getResult());
        clearVar(line.getResult());
      }
    }
  }

  public void generateCodeOperator (RowMip line) {
    if (line.getToken().equals(TokenMip.ADD)) {
      if (line.getValue1().charAt(0) != 't' && line.getToken().charAt(0) != 't') {

      }
    }
  }

  // funcion para la impresión de codigo
  private void generateCodePut (RowMip line) {
    if (line.getResult().charAt(0) == 'm') {
      addLine("li $v0, 4");
      addLine("la $a0, " + line.getResult());
    } else {
      addLine("li $v0, 1");
      addLine("lw $a0, _" + line.getResult());
    }
    addLine("syscall");
  }

  public void generateCode () {
    addLine(".data");
    // generando las declaraciones de los strings
    for (RowMip line : mips.codeString) {
      String code = "" + line.getResult() + ": .asciiz " + line.getValue1();
      addLine(code);
    }
    // generando todo el codigo
    for (RowMip line : mips.code) {
      // codigo de variables globales o iniciales
      if (line.getToken().equals(TokenMip.ASSIGN)) {
        generaCodeAssing(line);
      }
      if (!line.getToken().equals(TokenMip.ASSIGN) && initialVars == true) { // generando el codigo administrativo
        initialVars = false;
        String code1 = ".text";
        addLine(code1);
        String code2 = ".globl main";
        addLine(code2);
      }
      // generacion de etiquetas
      if (line.getToken().equals(TokenMip.ETIQ)) {
        if (line.getResult().equals(TokenMip.MAIN)) {
          addLine("main:");
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

  public void saveCodeFile (String name) {
    FileWriter fw = null;
    PrintWriter pw = null;
    try {
      fw = new FileWriter("./Ejecutables/" + name + ".asm");
      pw = new PrintWriter(fw);
      for (String line : this.lineCode) {
        pw.println(line);
      }
      System.out.println("\nSe ha creado el ejecutable\n");
      fw.close();
    } catch (Exception e) {
      System.out.println("Ocurrio un error al crear el ejecutable");
    }
  }

  public void printCode () {
    for (String line : lineCode) {
      System.out.println(line);
    }
  }

}