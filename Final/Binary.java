package Final;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import Intermedio.Mips;
import Intermedio.RowMip;
import Intermedio.TokenMip;
import Tabla.Simbolo;
import Tabla.TablaSimbolos;

public class Binary {

  public LinkedList<String> lineCode = new LinkedList<String>();
  public LinkedList<TempVar> $t = new LinkedList<TempVar>();
  public LinkedList<TempVar> $a = new LinkedList<TempVar>();
  public LinkedList<TempVar> $s = new LinkedList<TempVar>();
  public LinkedList<TempVar> varFunction = new LinkedList<TempVar>();
  private boolean initialParam = false;
  private String mainProcedure;
  private String procedure;
  public TablaSimbolos table;
  private int limitT = 8;
  private int limitA = 4;
  private int limitS = 8;
  private Mips mips;
  private boolean initialVars = true;
  private int initialSpace = 8;

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

  public Binary (Mips mips, TablaSimbolos table, String mainProcedure) {
    this.mips = mips;
    this.mainProcedure = mainProcedure;
    this.table = table;
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

  // funcion para llenar un parametro dentro de un funcion
  private TempVar addParamFunc (String temp) {
    for (TempVar var : $s) {
      if (var.getEmpty()) {
        var.addVar(temp);
        return var;
      }
    }
    return null;
  }

  // funcion para obtener un parametro
  private TempVar getParamFunc (String temp) {
    for (TempVar var : $s) {
      if (var.getVar().equals(temp)) {
        return var;
      }
    }
    return null;
  }

  // funcion para limpiar un parametro en especifico
  private boolean clearParam (String temp) {
    for (TempVar var : $s) {
      if (var.getVar().equals(temp)) {
        var.clearVar();
        return true;
      }
    }
    return false;
  }

  // funcion para limpiar todos lo parametros
  private void clearAllParams () {
    for (TempVar var : $s) {
      var.clearVar();
    }
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
  private void generaCodeAssing (RowMip line) {
    if (initialVars == true) { // generando variables globales
      addLine("_" + line.getResult() + ": .word " + line.getValue1());
    } else {
      Simbolo value = this.table.buscarSimbolo(procedure, line.getValue1());
      if (value == null) {
        Simbolo result = this.table.buscarSimbolo(procedure, line.getResult());
        if (result != null) {
          if (line.getValue1().charAt(0) != 't') { //  si es asignacion de variable temporal
            TempVar var = addVar(line.getResult());
            addLine("li " + var.getTemp() + ", " + line.getValue1());
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getResult());
          } else {
            TempVar var = getVar(line.getValue1());
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getValue1());
          }
        } else {
          TempVar temp = getVar(line.getResult());
          if (temp == null) {
            TempVar var = addVar(line.getResult());
            addLine("li " + var.getTemp() + ", " + line.getValue1());
          } else {
            addLine("li " + temp.getTemp() + ", " + line.getValue1());
          }
        }
      } else {
        Simbolo result = this.table.buscarSimbolo(procedure, line.getResult());
        if (result != null) {
          TempVar var = addVar(value.id);
          addLine("lw " + var.getTemp() + ", _" + value.id);
          addLine("sw " + var.getTemp() + ", _" + line.getResult());
          clearVar(value.id);
        } else {
          TempVar res = addVar(line.getResult());
          addLine("lw " + res.getTemp() + ", _" + line.getValue1());
        }
      }
    }
  }

  // funcion de operaciones aritmeticas
  private void generateCodeOperator (RowMip line) {
    if (line.getToken().equals(TokenMip.ADD)) { // suma
      Simbolo value1 = this.table.buscarSimbolo(procedure, line.getValue1());
      Simbolo value2 = this.table.buscarSimbolo(procedure, line.getValue2());
      if (value1 == null && value2 == null) { //  si ninguno es una variable existente
        if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = addVar(line.getResult());
          TempVar var1 = addVar("1");
          addLine("li " + var1.getTemp() + ", " + line.getValue1());
          addLine("add " + var.getTemp() + ", " + var1.getTemp() + ", " + line.getValue2());
          if (this.table.buscarSimbolo(procedure, line.getResult()) != null) {
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getResult());
          }
          clearVar("1");
        } else if (line.getValue1().charAt(0) == 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("add " + newVar.getTemp() + ", " + var.getTemp() + ", " +  line.getValue2());
          clearVar(line.getValue1());
        } else if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) == 't') {
          TempVar var = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          TempVar var1 = addVar("1");
          addLine("li " + var1.getTemp() + ", " + line.getValue1());
          addLine("add " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue2());
          clearVar("1");
        } else {
          TempVar var1 = getVar(line.getValue1());
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("add " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue1());
          clearVar(line.getValue2());
        }
      } else if (value1 != null && value2 == null) {
        TempVar var = addVar(value1.id);
        addLine("lw " + var.getTemp() + ", _" + value1.id);
        if (line.getValue2().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("add " + newVar.getTemp() + ", " + var.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue2());
        } else {
          TempVar newVar = addVar(line.getResult());
          addLine("add " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
        }
        clearVar(value1.id);
      } else if (value1 == null && value2 != null) {
        TempVar var = addVar(value2.id);
        addLine("lw " + var.getTemp() + ", _" + value2.id);
        if (line.getValue1().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("add " + newVar.getTemp() + ", " + var2.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue1());
        } else {
          TempVar newVar = addVar(line.getResult());
          TempVar var1 = addVar("1");
          addLine("add " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var.getTemp());
          clearVar("1");
        }
        clearVar(value2.id);
      } else {
        TempVar var1 = addVar(value1.id);
        addLine("lw " + var1.getTemp() + ", _" + value1.id);
        TempVar var2 = addVar(value2.id);
        addLine("lw " + var2.getTemp() + ", _" + value2.id);
        TempVar newVar = addVar(line.getResult());
        addLine("add " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
        clearVar(value1.id);
        clearVar(value2.id);
      }
    } else if (line.getToken().equals(TokenMip.SUBTRACT)) { // resta
      Simbolo value1 = this.table.buscarSimbolo(procedure, line.getValue1());
      Simbolo value2 = this.table.buscarSimbolo(procedure, line.getValue2());
      if (value1 == null && value2 == null) { //  si ninguno es una variable existente
        if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = addVar(line.getResult());
          TempVar val1 = addVar("2");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("sub " + var.getTemp() + ", " + val1.getTemp() + ", " + line.getValue2());
          addLine("sw " + var.getTemp() + ", _" + line.getResult());
          if (this.table.buscarSimbolo(procedure, line.getResult()) != null) {
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getResult());
          }
          clearVar("2");
        } else if (line.getValue1().charAt(0) == 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("sub " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
          clearVar(line.getValue1());
        } else if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) == 't') {
          TempVar var = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          TempVar var1 = addVar("2");
          System.out.println(line.getValue2());
          addLine("li " + var1.getTemp() + ", " + line.getValue1());
          addLine("sub " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue2());
          clearVar("2");
        } else {
          TempVar var1 = getVar(line.getValue1());
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("sub " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue1());
          clearVar(line.getValue2());
        }
      } else if (value1 != null && value2 == null) {
        TempVar var = addVar(value1.id);
        addLine("lw " + var.getTemp() + ", _" + value1.id);
        if (line.getValue2().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("sub " + newVar.getTemp() + ", " + var.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue2());
        } else {
          TempVar newVar = addVar(line.getResult());
          addLine("sub " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
        }
        clearVar(value1.id);
      } else if (value1 == null && value2 != null) {
        TempVar var = addVar(value2.id);
        addLine("lw " + var.getTemp() + ", _" + value2.id);
        if (line.getValue1().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("sub " + newVar.getTemp() + ", " + var2.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue1());
        } else {
          TempVar newVar = addVar(line.getResult());
          TempVar val1 = addVar("2");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("sub " + newVar.getTemp() + ", " + val1.getTemp() + ", " + var.getTemp());
          clearVar("2");
        }
        clearVar(value2.id);
      } else {
        TempVar var1 = addVar(value1.id);
        addLine("lw " + var1.getTemp() + ", _" + value1.id);
        TempVar var2 = addVar(value2.id);
        addLine("lw " + var2.getTemp() + ", _" + value2.id);
        TempVar newVar = addVar(line.getResult());
        addLine("sub " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
        clearVar(value1.id);
        clearVar(value2.id);
      }
    } else if (line.getToken().equals(TokenMip.MULTIPLY)) { // multiplicacion
      Simbolo value1 = this.table.buscarSimbolo(procedure, line.getValue1());
      Simbolo value2 = this.table.buscarSimbolo(procedure, line.getValue2());
      if (value1 == null && value2 == null) { //  si ninguno es una variable existente
        if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = addVar(line.getResult());
          TempVar val1 = addVar("3");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("mul " + var.getTemp() + ", " + val1.getTemp() + ", " + line.getValue2());
          addLine("sw " + var.getTemp() + ", _" + line.getResult());
          if (this.table.buscarSimbolo(procedure, line.getResult()) != null) {
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getResult());
          }
          clearVar("3");
        } else if (line.getValue1().charAt(0) == 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("mul " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
          clearVar(line.getValue1());
        } else if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) == 't') {
          TempVar var = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          TempVar var1 = addVar("3");
          System.out.println(line.getValue2());
          addLine("li " + var1.getTemp() + ", " + line.getValue1());
          addLine("mul " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue2());
          clearVar("3");
        } else {
          TempVar var1 = getVar(line.getValue1());
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("mul " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue1());
          clearVar(line.getValue2());
        }
      } else if (value1 != null && value2 == null) {
        TempVar var = addVar(value1.id);
        addLine("lw " + var.getTemp() + ", _" + value1.id);
        if (line.getValue2().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("mul " + newVar.getTemp() + ", " + var.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue2());
        } else {
          TempVar newVar = addVar(line.getResult());
          addLine("mul " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
        }
        clearVar(value1.id);
      } else if (value1 == null && value2 != null) {
        TempVar var = addVar(value2.id);
        addLine("lw " + var.getTemp() + ", _" + value2.id);
        if (line.getValue1().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("mul " + newVar.getTemp() + ", " + var2.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue1());
        } else {
          TempVar newVar = addVar(line.getResult());
          TempVar val1 = addVar("3");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("mul " + newVar.getTemp() + ", " + val1.getTemp() + ", " + var.getTemp());
          clearVar("3");
        }
        clearVar(value2.id);
      } else {
        TempVar var1 = addVar(value1.id);
        addLine("lw " + var1.getTemp() + ", _" + value1.id);
        TempVar var2 = addVar(value2.id);
        addLine("lw " + var2.getTemp() + ", _" + value2.id);
        TempVar newVar = addVar(line.getResult());
        addLine("mul " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
        clearVar(value1.id);
        clearVar(value2.id);
      }
    } else {
      Simbolo value1 = this.table.buscarSimbolo(procedure, line.getValue1());
      Simbolo value2 = this.table.buscarSimbolo(procedure, line.getValue2());
      if (value1 == null && value2 == null) { //  si ninguno es una variable existente
        if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = addVar(line.getResult());
          TempVar val1 = addVar("4");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("div " + var.getTemp() + ", " + val1.getTemp() + ", " + line.getValue2());
          addLine("sw " + var.getTemp() + ", _" + line.getResult());
          if (this.table.buscarSimbolo(procedure, line.getResult()) != null) {
            addLine("sw " + var.getTemp() + ", _" + line.getResult());
            clearVar(line.getResult());
          }
          clearVar("4");
        } else if (line.getValue1().charAt(0) == 't' && line.getValue2().charAt(0) != 't') {
          TempVar var = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("div " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
          clearVar(line.getValue1());
        } else if (line.getValue1().charAt(0) != 't' && line.getValue2().charAt(0) == 't') {
          TempVar var = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          TempVar var1 = addVar("4");
          System.out.println(line.getValue2());
          addLine("li " + var1.getTemp() + ", " + line.getValue1());
          addLine("div " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue2());
          clearVar("4");
        } else {
          TempVar var1 = getVar(line.getValue1());
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("div " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue1());
          clearVar(line.getValue2());
        }
      } else if (value1 != null && value2 == null) {
        TempVar var = addVar(value1.id);
        addLine("lw " + var.getTemp() + ", _" + value1.id);
        if (line.getValue2().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue2());
          TempVar newVar = addVar(line.getResult());
          addLine("div " + newVar.getTemp() + ", " + var.getTemp() + ", " + var2.getTemp());
          clearVar(line.getValue2());
        } else {
          TempVar newVar = addVar(line.getResult());
          addLine("div " + newVar.getTemp() + ", " + var.getTemp() + ", " + line.getValue2());
        }
        clearVar(value1.id);
      } else if (value1 == null && value2 != null) {
        TempVar var = addVar(value2.id);
        addLine("lw " + var.getTemp() + ", _" + value2.id);
        if (line.getValue1().charAt(0) == 't') {
          TempVar var2 = getVar(line.getValue1());
          TempVar newVar = addVar(line.getResult());
          addLine("div " + newVar.getTemp() + ", " + var2.getTemp() + ", " + var.getTemp());
          clearVar(line.getValue1());
        } else {
          TempVar newVar = addVar(line.getResult());
          TempVar val1 = addVar("4");
          addLine("li " + val1.getTemp() + ", " + line.getValue1());
          addLine("div " + newVar.getTemp() + ", " + val1.getTemp() + ", " + var.getTemp());
          clearVar("4");
        }
        clearVar(value2.id);
      } else {
        TempVar var1 = addVar(value1.id);
        addLine("lw " + var1.getTemp() + ", _" + value1.id);
        TempVar var2 = addVar(value2.id);
        addLine("lw " + var2.getTemp() + ", _" + value2.id);
        TempVar newVar = addVar(line.getResult());
        addLine("div " + newVar.getTemp() + ", " + var1.getTemp() + ", " + var2.getTemp());
        clearVar(value1.id);
        clearVar(value2.id);
      }
    }
  }

  // funciones para las condicionales
  private void generateCodeCond (RowMip line) {
    Simbolo value1 = table.buscarSimbolo(procedure, line.getValue1());
    Simbolo value2 = table.buscarSimbolo(procedure, line.getValue2());
    if (line.getToken().equals(TokenMip.IFAND)) { //  OPERADOR AND
      TempVar val1 = getVar(line.getValue1());
      TempVar val2 = getVar(line.getValue2());
      TempVar result = addVar(line.getResult());
      addLine("and " + result.getTemp() + ", " + val1.getTemp() + ", " + val2.getTemp());
      clearVar(val1.getVar());
      clearVar(val2.getVar());
    } else if (line.getToken().equals(TokenMip.IFD)) { // Diferente
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bne " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", _" + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bne " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("bne " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      }
    } else if (line.getToken().equals(TokenMip.IFE)) { // igual
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("beq " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", " + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("beq " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("beq " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      } else {
        TempVar val1 = getVar(line.getValue1());
        addLine("beq " + val1.getTemp() + ", " + line.getValue2() + ", _" + line.getResult());
        clearVar(line.getValue1());
      }
    } else if (line.getToken().equals(TokenMip.IFH)) { // mayor que
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bgt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", " + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bgt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("bgt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      }
    } else if (line.getToken().equals(TokenMip.IFHE)) { // mayor o igual que
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bge " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", " + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("bge " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("bge " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      }
    } else if (line.getToken().equals(TokenMip.IFL)) { // menor que
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("blt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", " + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("blt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("blt " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      }
    } else if (line.getToken().equals(TokenMip.IFLE)) { // menor o igual que
      if (value1 != null && value2 != null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar(value2.id);
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("ble " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar(value1.id);
        clearVar(value2.id);
      } else if (value1 == null && value2 != null) {
        TempVar val1 = addVar("1");
        TempVar val2 = addVar(value2.id);
        addLine("li " + val1.getTemp() + ", " + line.getValue1());
        addLine("lw " + val2.getTemp() + ", _" + value2.id);
        addLine("ble " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value2.id);
      } else if (value1 != null && value2 == null) {
        TempVar val1 = addVar(value1.id);
        TempVar val2 = addVar("1");
        addLine("lw " + val1.getTemp() + ", _" + value1.id);
        addLine("li " + val2.getTemp() + ", " + line.getValue2());
        addLine("ble " + val1.getTemp() + ", " + val2.getTemp() + ", _" + line.getResult());
        clearVar("1");
        clearVar(value1.id);
      }
    } else if (line.getToken().equals(TokenMip.IFOR)) { // OPERADOR OR
      TempVar val1 = getVar(line.getValue1());
      TempVar val2 = getVar(line.getValue2());
      TempVar result = addVar(line.getResult());
      addLine("or " + result.getTemp() + ", " + val1.getTemp() + ", " + val2.getTemp());
      clearVar(val1.getVar());
      clearVar(val2.getVar());
    } else { // GOTO
      addLine("b _" + line.getResult());
    }
  }

  // funcion para la impresión de codigo
  private void generateCodePut (RowMip line) {
    Simbolo value = this.table.buscarSimbolo(procedure, line.getResult());
    if (value != null) {
      addLine("li $v0, 1");
      addLine("lw $a0, _" + line.getResult());
    } else {
      addLine("li $v0, 4");
      addLine("la $a0, " + line.getResult());
    }
    addLine("syscall");
  }

  // funcion para obtener un dato
  private void generateCodeGet (RowMip line) {
    addLine("li $v0, 5");
    addLine("syscall");
    addLine("sw $v0, _" + line.getResult());
  }


  // function para el

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
      if (line.getToken().equals(TokenMip.ASSIGN) && !initialParam) {
        generaCodeAssing(line);
      }
      if (!line.getToken().equals(TokenMip.ASSIGN) && initialVars) { // generando el codigo administrativo
        initialVars = false;
        String code1 = ".text";
        addLine(code1);
        String code2 = ".globl main";
        addLine(code2);
      }
      // generacion de etiquetas
      if (line.getToken().equals(TokenMip.ETIQ)) {
        if (line.getResult().equals(TokenMip.MAIN)) {
          this.procedure = this.mainProcedure;
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
      // para operaciones
      if (line.getToken().equals(TokenMip.ADD) || line.getToken().equals(TokenMip.SUBTRACT) ||
          line.getToken().equals(TokenMip.MULTIPLY) || line.getToken().equals(TokenMip.DIVIDE)) {
        generateCodeOperator(line);
      }
      // para condicionales
      if (line.getToken().equals(TokenMip.IFAND) || line.getToken().equals(TokenMip.IFD) ||
          line.getToken().equals(TokenMip.IFE) || line.getToken().equals(TokenMip.IFH) ||
          line.getToken().equals(TokenMip.IFHE) || line.getToken().equals(TokenMip.IFL) ||
          line.getToken().equals(TokenMip.IFLE) || line.getToken().equals(TokenMip.IFOR) ||
          line.getToken().equals(TokenMip.GOTO)) {
        generateCodeCond(line);
      }
      // generacion de codigo para el token get
      if (line.getToken().equals(TokenMip.GET)) {
        generateCodeGet(line);
      }
      if (line.getToken().equals(TokenMip.FUNC)) {
        addLine("_" + line.getResult() + ":");
        addLine("sw $fp, -4($sp)");
        addLine("sw $ra, -8($sp)");
        procedure = line.getResult();
        initialParam = true;
      }
      if (line.getToken().equals(TokenMip.PARAM)) {
        if (initialParam) {
          TempVar param = addParamFunc(line.getResult());
          initialSpace += 4;
          addLine("sw " + param.getTemp() + ", -" + initialSpace + "($sp)");
        }
      } else {
        if (line.getToken().equals(TokenMip.ASSIGN) && initialParam) {
          initialSpace += 4;
        }
        if (!line.getToken().equals(TokenMip.ASSIGN) && initialParam) {
          addLine("move $fp, $sp");
          for (int i = 0; i < $s.size(); i++) {
            TempVar var = $s.get(i);
            if (!var.getEmpty()) {
              addLine("move " + var.getTemp() + ", $a" + i);
            }
          }
          addLine("sub $sp, $sp, " + initialSpace);
        }
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