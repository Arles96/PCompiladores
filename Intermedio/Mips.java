package Intermedio;

import java.util.LinkedList;
import Nodos.*;

public class Mips {

  public LinkedList <RowMip> code = new LinkedList<RowMip>();
  public LinkedList <RowMip> codeString = new LinkedList<RowMip>();
  public LinkedList <RowMip> initialVariables = new LinkedList<RowMip>();
  private int counterVar = 0;
  private int counterEt = 0;
  private int counterString = 0;
  private String etiqTrue = "";
  private LinkedList <String> etiqsFalse = new LinkedList<String>();

  public Mips () {}

  public void addRow (RowMip r) {
    code.add(r);
  }

  public void addRowString (RowMip e) {
    codeString.add(e);
  }

  public void incrementVar () {
    counterVar++;
  }

  public void incrementString () {
    counterString++;
  }

  public String getTempString () {
    return "mgs" + counterString;
  }

  public String getTempVar () {
    return "t" + counterVar;
  }

  public String getTempEtiq () {
    return "etiq" + counterEt;
  }

  public void incrementEt () {
    counterEt++;
  }

  public int getCounterVar () {
    return this.counterVar;
  }

  public int getCounterEt () {
    return this.counterEt;
  }

  // generar codigo intermedio para operaciones aritmeticas
  private void genarateCodeOperation (Nodo id, Nodo nodo, int n) {
    String t1 = null, t2 = null;
    Nodo hijo1 = nodo.hijos.get(0);
    Nodo hijo2 = nodo.hijos.get(1);
    if (hijo1.tag.equals(TagAbstract.OP) && hijo2.tag.equals(TagAbstract.OP)) {
      genarateCodeOperation(id, hijo1, 1);
      t1 = getTempVar();
      incrementVar();
      genarateCodeOperation(id, hijo2, 1);
      t2 = getTempVar();
      incrementVar();
    }
    if ((hijo1.tag.equals(TagAbstract.NUM) || hijo1.tag.equals(TagAbstract.ID)) && (hijo2.tag.equals(TagAbstract.NUM) || hijo2.tag.equals(TagAbstract.ID))) {
      if (n == 1) {
        if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.NUM)) {
          addRow(new RowMip(nodo.valor, hijo1.valor, hijo2.valor, getTempVar()));
        } else if (hijo1.tag.equals(TagAbstract.ID) && hijo2.tag.equals(TagAbstract.NUM)) {
          addRow(new RowMip(TokenMip.ASSIGN, hijo1.valor, getTempVar()));
          String temp = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, temp, hijo2.valor, getTempVar()));
        } else if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.ID)) {
          addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, getTempVar()));
          String temp = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, hijo2.valor, temp, getTempVar()));
        } else {
          addRow(new RowMip(TokenMip.ASSIGN, hijo1.valor, getTempVar()));
          String temp1 = getTempVar();
          incrementVar();
          addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, getTempVar()));
          String temp2 = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, temp1, temp2, getTempVar()));
        }
      } else {
        if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.NUM)) {
          addRow(new RowMip(nodo.valor, hijo1.valor, hijo2.valor, getTempVar()));
        } else if (hijo1.tag.equals(TagAbstract.ID) && hijo2.tag.equals(TagAbstract.NUM)) {
          addRow(new RowMip(TokenMip.ASSIGN, hijo1.valor, getTempVar()));
          String temp = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, temp, hijo2.valor, getTempVar()));
        } else if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.ID)) {
          addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, getTempVar()));
          String temp = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, hijo2.valor, temp, getTempVar()));
        } else {
          addRow(new RowMip(TokenMip.ASSIGN, hijo1.valor, getTempVar()));
          String temp1 = getTempVar();
          incrementVar();
          addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, getTempVar()));
          String temp2 = getTempVar();
          incrementVar();
          addRow(new RowMip(nodo.valor, temp1, temp2, getTempVar()));
        }
      }
    }
    if (hijo1.tag.equals(TagAbstract.OP) && (hijo2.tag.equals(TagAbstract.NUM) || hijo1.tag.equals(TagAbstract.ID))) {
      genarateCodeOperation(id, hijo1, 1);
      t1 = getTempVar();
      incrementVar();
      if (hijo2.tag.equals(TagAbstract.NUM)) {
        addRow(new RowMip(nodo.valor, t1, hijo2.valor, getTempVar()));
      } else {
        addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, getTempVar()));
        String temp2 = getTempVar();
        incrementVar();
        addRow(new RowMip(nodo.valor, t1, temp2, getTempVar()));
      }
      t1 = null;
      t2 = getTempVar();
      incrementVar();
    }
    if ((hijo1.tag.equals(TagAbstract.NUM) || hijo1.tag.equals(TagAbstract.ID)) && hijo2.tag.equals(TagAbstract.OP)) {
      genarateCodeOperation(id, hijo2, 1);
      t2 = getTempVar();
      incrementVar();
      if (hijo1.tag.equals(TagAbstract.NUM)) {
        addRow(new RowMip(nodo.valor, hijo1.valor, t2, getTempVar()));
      } else {
        addRow(new RowMip(TokenMip.ASSIGN, hijo1.valor, getTempVar()));
        String temp1 = getTempVar();
        incrementVar();
        addRow(new RowMip(nodo.valor, temp1, t2, getTempVar()));
      }
      t2 = null;
      t1 = getTempVar();
      incrementVar();
    }
    if (n == 0) {
      if (t1 != null && t2 != null) {
        addRow(new RowMip(nodo.valor, t1, t2, id.valor));
      } else if (t1 == null && t2 != null) {
        addRow(new RowMip(TokenMip.ASSIGN, t2, id.valor));
      } else if (t1 != null && t2 == null) {
        addRow(new RowMip(TokenMip.ASSIGN, t1, id.valor));
      } else {
        addRow(new RowMip(TokenMip.ASSIGN, getTempVar(), id.valor));
        incrementVar();
      }
    }
  }

  // generar codigo para condicionales
  public String generateCodeCond (Nodo nodo, int n) {
    String t1 = null, t2 = null, etiq1 = null, etiq2 = null;
    if (nodo.tag.equals(TagAbstract.OR) || nodo.tag.equals(TagAbstract.AND)) {
      Nodo hijo1 = nodo.hijos.get(0);
      Nodo hijo2 = nodo.hijos.get(1);
      if ((hijo1.tag.equals(TagAbstract.AND) || hijo2.tag.equals(TagAbstract.OR)) && hijo2.tag.equals(TagAbstract.OPREL)) {
        t1 = generateCodeCond(hijo1, 1);
        Nodo value1 = hijo2.hijos.get(0);
        Nodo value2 = hijo2.hijos.get(1);
        // evaluamos la condicional
        addRow(new RowMip("IF" + hijo2.valor, value1.valor, value2.valor, getTempEtiq()));
        etiq1 = getTempEtiq();
        incrementEt();
        // si es falso
        addRow(new RowMip(TokenMip.ASSIGN, "0", getTempVar()));
        t2 = getTempVar();
        incrementVar();
        addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
        etiq2 = getTempEtiq();
        incrementEt();
        // si es verdadero
        addRow(new RowMip(TokenMip.ETIQ, etiq1));
        addRow(new RowMip(TokenMip.ASSIGN, "1", t2));
        // creacion del la siguiente etiqueta
        addRow(new RowMip(TokenMip.ETIQ, etiq2));
        addRow(new RowMip("IF" + nodo.tag, t1, t2, getTempVar()));
        t1 = getTempVar();
        incrementVar();
        if (n == 1) {
          addRow(new RowMip(TokenMip.IFE, t1, "1", getTempEtiq()));
          etiq1 = getTempEtiq();
          incrementEt();
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return t1;
        } else {
          addRow(new RowMip(TokenMip.IFE, t1, "1", etiqTrue));
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return "";
        }
      }
      if (hijo1.tag.equals(TagAbstract.OPREL) && (hijo2.tag.equals(TagAbstract.OR) || hijo2.tag.equals(TagAbstract.AND))) {
        Nodo value1 = hijo1.hijos.get(0);
        Nodo value2 = hijo1.hijos.get(1);
        // genero el condicional
        addRow(new RowMip("IF" + hijo1.valor, value1.valor, value2.valor, getTempEtiq()));
        etiq1 = getTempEtiq();
        incrementEt();
        // asigno false a la variable
        addRow(new RowMip(TokenMip.ASSIGN, "0", getTempVar()));
        t1 = getTempVar();
        incrementVar();
        addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
        etiq2 = getTempEtiq();
        incrementEt();
        // si es verdadero
        addRow(new RowMip(TokenMip.ETIQ, etiq1));
        addRow(new RowMip(TokenMip.ASSIGN, "1", t1));
        // genero la siguiente eiqueta
        addRow(new RowMip(TokenMip.ETIQ, etiq2));
        t2 = generateCodeCond(hijo2, 1);
        // evalue con el padre
        addRow(new RowMip("IF" + nodo.tag, t1, t2, getTempVar()));
        t1 = getTempVar();
        incrementVar();
        if (n == 1) {
          addRow(new RowMip(TokenMip.IFE, t1, "1", getTempEtiq()));
          etiq1 = getTempEtiq();
          incrementEt();
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return t1;
        } else {
          addRow(new RowMip(TokenMip.IFE, t1, "1", etiqTrue));
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return "";
        }
      }
      if (hijo1.tag.equals(TagAbstract.OPREL) && hijo2.tag.equals(TagAbstract.OPREL)) {
        Nodo value1 = hijo1.hijos.get(0);
        Nodo value2 = hijo1.hijos.get(1);
        Nodo value3 = hijo2.hijos.get(0);
        Nodo value4 = hijo2.hijos.get(1);
        /* condicional del primer argumento */
        // creacion del condicional
        addRow(new RowMip("IF" + hijo1.valor, value1.valor, value2.valor, getTempEtiq()));
        etiq1 = getTempEtiq();
        incrementEt();
        // asignacion de falso
        addRow(new RowMip(TokenMip.ASSIGN, "0", getTempVar()));
        t1 = getTempVar();
        incrementVar();
        addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
        etiq2 = getTempEtiq();
        incrementEt();
        // si es verdadero
        addRow(new RowMip(TokenMip.ETIQ, etiq1));
        addRow(new RowMip(TokenMip.ASSIGN, "1", t1));
        // creacion de la siguiente etiqueta
        addRow(new RowMip(TokenMip.ETIQ, etiq2));

        /* Condicional del segundo argumento */
        addRow(new RowMip("IF" + hijo2.valor, value3.valor, value4.valor, getTempEtiq()));
        etiq1 = getTempEtiq();
        incrementEt();
        // si el segundo argumento es falso
        addRow(new RowMip(TokenMip.ASSIGN, "0", getTempVar()));
        t2 = getTempVar();
        incrementVar();
        addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
        etiq2 = getTempEtiq();
        incrementEt();
        // si el argumento es verdadero
        addRow(new RowMip(TokenMip.ETIQ, etiq1));
        addRow(new RowMip(TokenMip.ASSIGN, "1", t2));
        // creación de la siguiente etiqueta
        addRow(new RowMip(TokenMip.ETIQ, etiq2));

        // operacion and u or
        addRow(new RowMip("IF" + nodo.tag, t1, t2, getTempVar()));
        t1 = getTempVar();
        incrementVar();
        if (n == 1) {
          addRow(new RowMip(TokenMip.IFE, t1, "1", getTempEtiq()));
          etiq1 = getTempEtiq();
          incrementEt();
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return t1;
        } else {
          addRow(new RowMip(TokenMip.IFE, t1, "1", etiqTrue));
          addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
          return "";
        }
      }
    } else {
      Nodo hijo1 = nodo.hijos.get(0);
      Nodo hijo2 = nodo.hijos.get(1);
      addRow(new RowMip("IF" + nodo.valor, hijo1.valor, hijo2.valor, etiqTrue));
      addRow(new RowMip(TokenMip.GOTO, etiqsFalse.get(etiqsFalse.size() - 1)));
    }
    return "";
  }

  // generar codigo intermedio de parametros
  public void generateCodeParam (Nodo nodo) {
    for (Nodo var : nodo.hijos) {
      if (var.tag.equals(TagAbstract.DATATYPE)) {
        generateCodeParam(var);
      } else if (var.tag.equals(TagAbstract.ID)) {
        addRow(new RowMip(TokenMip.PARAM, var.valor));
      }
    }
  }

  // generar codigo intermedio total
  public void generateCode (Nodo tree) {
    if (tree.tag.equals(TagAbstract.INICIO)) {
      for (Nodo nodo : tree.hijos) {
        generateCode(nodo);
      }
    } else if (tree.tag.equals(TagAbstract.PROCEDURE)) {
      for (Nodo nodo : tree.hijos) {
        generateCode(nodo);
      }
    } else if (tree.tag.equals(TagAbstract.DATATYPE)) {
      Nodo h1 = tree.hijos.get(0);
      Nodo h2 = tree.hijos.get(1);
      if (h1.tag.equals(TagAbstract.ID) && h2.tag.equals(TagAbstract.NULO)) { // declaracion de una variable sin asignacion
        if (tree.valor.equals("Boolean")) {
          addRow(new RowMip(TokenMip.ASSIGN, "FALSE", tree.hijos.get(0).valor));
        } else if (tree.valor.equals(TagAbstract.INTEGER)) {
          addRow(new RowMip(TokenMip.ASSIGN, "0", tree.hijos.get(0).valor));
        }
      } else if (h1.tag.equals(TagAbstract.ID) && h2.tag.equals(TagAbstract.ASSIGN)) { // declaracion de una variable cons asignacion
          Nodo value = h2.hijos.get(0);
          addRow(new RowMip(TokenMip.ASSIGN, value.valor, tree.hijos.get(0).valor));
      } else if (h1.tag.equals(TagAbstract.DECLARACION) && h2.tag.equals(TagAbstract.NULO)) { // declaracion de lista de variables sin asignacion
        if (tree.valor.equalsIgnoreCase("Boolean")) {
          for (Nodo var : h1.hijos) {
            addRow(new RowMip(TokenMip.ASSIGN, "FALSE", var.valor));
          }
        } else if (tree.valor.equalsIgnoreCase("INTEGER")) {
          for (Nodo var : h1.hijos) {
            addRow(new RowMip(TokenMip.ASSIGN, "0", var.valor));
          }
        }
      } else if (h1.tag.equals(TagAbstract.DECLARACION) && h2.tag.equals(TagAbstract.ASSIGN)) { // declaracion de lista de variables con asignacion
        for (Nodo var : h1.hijos) {
          addRow(new RowMip(TokenMip.ASSIGN, h2.hijos.get(0).valor, var.valor));
        }
      }
    } else if (tree.tag.equals(TagAbstract.CUERPO)) {
      if (tree.padre.tag.equals(TagAbstract.PROCEDURE)) {
        addRow(new RowMip(TokenMip.ETIQ, TokenMip.MAIN));
      }
      for (Nodo nodo : tree.hijos) {
        generateCode(nodo);
      }
    } else if (tree.tag.equals(TagAbstract.ASSINGVALUE)) { //  asignacion para un valor o asignacion aritmetica
      Nodo hijo1 = tree.hijos.get(0); // id
      Nodo hijo2 = tree.hijos.get(1); // valor u operador
      if (hijo2.hijos.size() == 0 && hijo2.tag.equals(TagAbstract.NUM))  { // Asignar numeros
        addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, hijo1.valor));
      } else if (hijo2.hijos.size() == 0 && hijo2.tag.equals(TagAbstract.FALSE)) { // Asignar booleanos false
        addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, hijo1.valor));
      } else if (hijo2.hijos.size() == 0 && hijo2.tag.equals(TagAbstract.TRUE)) { // Asignar booleanos TRUE
        addRow(new RowMip(TokenMip.ASSIGN, hijo2.valor, hijo1.valor));
      } else {
        genarateCodeOperation(hijo1, hijo2, 0);
      }
    } else if (tree.tag.equals(TagAbstract.IF) || tree.tag.equals(TagAbstract.ELSEIF)) { // condicionales
      Nodo hijo1 = tree.hijos.get(0);
      Nodo hijo2 = tree.hijos.get(1);
      etiqTrue = getTempEtiq();
      incrementEt();
      if (tree.tag.equals(TagAbstract.IF)) {
        etiqsFalse.push(getTempEtiq());
        incrementEt();
      }
      // generar codigo intermedio de los operadores logicos
      generateCodeCond(hijo1, 0);
      addRow(new RowMip(TokenMip.ETIQ, etiqTrue));
      etiqTrue = null;
      for (Nodo nod : hijo2.hijos) {
        generateCode(nod);
      }
      if (etiqsFalse.size() > 0) {
        addRow(new RowMip(TokenMip.ETIQ, etiqsFalse.pop()));
      }
    } else if (tree.tag.equals(TagAbstract.ELSE)) {
      addRow(new RowMip(TokenMip.ETIQ, etiqsFalse.pop()));
      for (Nodo nod : tree.hijos) {
        generateCode(nod);
      }
    } else if (tree.tag.equals(TagAbstract.FOR)) { // para el for
      Nodo hijo1 = tree.hijos.get(0);
      Nodo hijo2 = tree.hijos.get(1);
      Nodo hijo3 = tree.hijos.get(2);
      String [] values = hijo2.valor.split("-");
      String firstValue = values[0];
      String endValue = values[1];
      // asignar los valores
      addRow(new RowMip(TokenMip.ASSIGN, firstValue, hijo1.valor));
      /* addRow(new RowMip(TokenMip.ASSIGN, endValue, getTempVar()));
      String limit = getTempVar();
      incrementVar(); */
      // comparacion
      addRow(new RowMip(TokenMip.ETIQ, getTempEtiq()));
      String compare = getTempEtiq();
      incrementEt();
      addRow(new RowMip(TokenMip.IFLE, hijo1.valor, endValue, getTempEtiq()));
      String body = getTempEtiq();
      incrementEt();
      addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
      String next = getTempEtiq();
      // cuerpo
      addRow(new RowMip(TokenMip.ETIQ, body));
      for (Nodo nod : hijo3.hijos) {
        generateCode(nod);
      }
      addRow(new RowMip(TokenMip.ADD, hijo1.valor, "1", getTempVar()));
      String plus = getTempVar();
      incrementVar();
      addRow(new RowMip(TokenMip.ASSIGN, plus, hijo1.valor));
      addRow(new RowMip(TokenMip.GOTO, compare));
      // siguiente
      addRow(new RowMip(TokenMip.ETIQ, next));
    } else if (tree.tag.equals(TagAbstract.LOOP)) {
      addRow(new RowMip(TokenMip.ETIQ, getTempEtiq()));
      String begin = getTempEtiq();
      incrementEt();
      for (Nodo var : tree.hijos) {
        generateCode(var);
      }
      addRow(new RowMip(TokenMip.GOTO, begin));
      addRow(new RowMip(TokenMip.ETIQ, getTempEtiq()));
      incrementEt();
    } else if (tree.tag.equals(TagAbstract.WHILE)) { // para el while
      Nodo hijo1 = tree.hijos.get(0);
      Nodo hijo2 = tree.hijos.get(1);
      addRow(new RowMip(TokenMip.ETIQ, getTempEtiq()));
      String begin = getTempEtiq();
      incrementEt();
      etiqTrue = getTempEtiq();
      incrementEt();
      etiqsFalse.push(getTempEtiq());
      incrementEt();
      generateCodeCond(hijo1, 0);
      addRow(new RowMip(TokenMip.ETIQ, etiqTrue));
      etiqTrue = null;
      for (Nodo var : hijo2.hijos) {
        generateCode(var);
      }
      addRow(new RowMip(TokenMip.GOTO, begin));
      addRow(new RowMip(TokenMip.ETIQ, etiqsFalse.pop()));
    } else if (tree.tag.equals(TagAbstract.SUBPROCEDURE) || tree.tag.equals(TagAbstract.FUNCTION)) {
      addRow(new RowMip(TokenMip.FUNC, tree.hijos.get(0).valor));
      for (Nodo var : tree.hijos) {
        if (var.tag.equals(TagAbstract.PARAM)) { // si es parametros
          for (Nodo child : var.hijos) {
            if (child.tag.equals(TagAbstract.DECLARACION)) {
              for (Nodo child2 : child.hijos) {
                addRow(new RowMip(TokenMip.PARAM, child2.valor));
              }
            } else if (child.tag.equals(TagAbstract.DATATYPE)) {
              generateCodeParam(child);
            }
          }
        } else if (var.tag.equals(TagAbstract.TEM)) { // declaracion de variables, funciones y procedimientos
          for (Nodo child : var.hijos) {
            generateCode(child);
          }
        } else if (var.tag.equals(TagAbstract.CUERPO)) { // cuerpo de la funcion o procedimiento
          for (Nodo child : var.hijos) {
            generateCode(child);
          }
        }
      }
    } else if (tree.tag.equals(TagAbstract.RETURN)) { //  al retornar en una funcion
      Nodo hijo1 = tree.hijos.get(0);
      addRow(new RowMip(TokenMip.RETURN, hijo1.valor));
    } else if (tree.tag.equals(TagAbstract.PUT)){ //  la funcion put
      Nodo value = tree.hijos.get(0);
      if (value.tag.equals(TagAbstract.STRING)) {
        addRowString(new RowMip(TokenMip.ASSIGN, value.valor, getTempString()));
        addRow(new RowMip(TokenMip.PUT, getTempString()));
        incrementString();
      } else if (value.tag.equals(TagAbstract.NUM)) {
        addRow(new RowMip(TokenMip.ASSIGN, value.valor, getTempVar()));
        addRow(new RowMip(TokenMip.PUT, getTempVar()));
        incrementVar();
      } else if (value.tag.equals(TagAbstract.ID)) {
        addRow(new RowMip(TokenMip.PUT, value.valor));
      }
    } else if (tree.tag.equals(TagAbstract.GET)) { // la funcion get
      addRow(new RowMip(TokenMip.GET, tree.valor));
    } else if (tree.tag.equals(TagAbstract.EJECUCION)) {
      Nodo h1 = tree.hijos.get(0);
      Nodo h2 = tree.hijos.get(1);
      if (h2.tag.equals(TagAbstract.ID)) {
        addRow(new RowMip(TokenMip.PARAM, h2.valor));
      } else if (h2.tag.equals(TagAbstract.ARGUMENTOS)) {
        for (Nodo var : h2.hijos) {
          addRow(new RowMip(TokenMip.PARAM, var.valor));
        }
      }
      addRow(new RowMip(TokenMip.EXEC, h1.valor));
    }
  }

  public void printCode () {
    for (RowMip var : codeString) {
      System.out.println(var.toString());
    }
    for (RowMip row : code) {
      System.out.println(row.toString());
    }
  }

}