package Intermedio;

import java.util.LinkedList;
import Nodos.*;

public class Mips {

  public LinkedList <RowMip> code = new LinkedList<RowMip>();
  public LinkedList <RowMip> initialVariables = new LinkedList<RowMip>();
  private int counterVar = 0;
  private int counterEt = 0;

  public Mips () {}

  public void addRow (RowMip r) {
    code.add(r);
  }

  public void incrementVar () {
    counterVar++;
  }

  public String getTempVar () {
    return "t" + counterVar;
  }

  public String getTempEtiq () {
    return "t" + counterEt;
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
    if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.NUM)) {
      if (n == 1) {
        addRow(new RowMip(nodo.valor, hijo1.valor, hijo2.valor, getTempVar()));
      } else {
        addRow(new RowMip(nodo.valor, hijo1.valor, hijo2.valor, id.valor));
      }
    }
    if (hijo1.tag.equals(TagAbstract.OP) && hijo2.tag.equals(TagAbstract.NUM)) {
      genarateCodeOperation(id, hijo1, 1);
      t1 = getTempVar();
      incrementVar();
      addRow(new RowMip(nodo.valor, t1, hijo2.valor, getTempVar()));
      t1 = null;
      t2 = getTempVar();
      incrementVar();

    }
    if (hijo1.tag.equals(TagAbstract.NUM) && hijo2.tag.equals(TagAbstract.OP)) {
      genarateCodeOperation(id, hijo2, 1);
      t2 = getTempVar();
      incrementVar();
      addRow(new RowMip(nodo.valor, hijo1.valor, t2, getTempVar()));
      t2 = null;
      t1 = getTempVar();
      incrementVar();
    }
    if (n == 0) {
      if (t1 != null && t2 != null) {
        addRow(new RowMip(nodo.valor, t1, t2, id.valor));
      } else if (t1 == null && t2 != null) {
        addRow(new RowMip(nodo.valor, t2, id.valor));
      } else if (t1 != null && t2 == null) {
        addRow(new RowMip(nodo.valor, t1, id.valor));
      } else {
        System.out.println("Error en la sección de operaciones aritmeticas");
      }
    }
  }

  // generar codigo para condicionales
  public void generateCodeCond (Nodo nodo, int n) {
    String t1 = null, t2 = null, etiq1 = null;
    if (nodo.tag.equals(TagAbstract.AND) || nodo.tag.equals(TagAbstract.OR)) {
      Nodo hijo1 = nodo.hijos.get(0);
      Nodo hijo2 = nodo.hijos.get(1);
      /* if (hijo1.tag.equals(TagAbstract.AND) && hijo2.tag.equals(TagAbstract.OPREL)) {
        generateCodeCond(hijo1, 1);
        Nodo value1 = hijo2.hijos.get(0);
        Nodo value2 = hijo2.hijos.get(1);
      } */
      if (hijo1.tag.equals(TagAbstract.OPREL) && hijo2.tag.equals(TagAbstract.AND)) {
        Nodo value1 = hijo1.hijos.get(0);
        Nodo value2 = hijo1.hijos.get(1);
        addRow(new RowMip("IF" + hijo1.valor, value1.valor, value2.valor, getTempEtiq()));
        etiq1 = getTempEtiq();
        incrementEt();
        addRow(new RowMip(TokenMip.ASSIGN, "0", getTempVar()));
        t1 = getTempVar();
        incrementVar();
        addRow(new RowMip(TokenMip.GOTO, getTempEtiq()));
        addRow(new RowMip(TokenMip.ETIQ, etiq1));
        addRow(new RowMip(TokenMip.ASSIGN, "1", t1));
        generateCodeCond(hijo2, 1);
      } else if (hijo1.tag.equals(TagAbstract.OPREL) && hijo2.tag.equals(TagAbstract.OR)) {

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
        } else if (tree.valor.equals("INTEGER")) {
          addRow(new RowMip(TokenMip.ASSIGN, "0", tree.hijos.get(0).valor));
        }
      } else if (h1.tag.equals(TagAbstract.ID) && h2.tag.equals(TagAbstract.ASSIGN)) { // declaracion de una variable cons asignacion
          Nodo value = tree.hijos.get(1);
          addRow(new RowMip(TokenMip.ASSIGN, value.valor, tree.hijos.get(0).valor));
      } else if (h1.tag.equals(TagAbstract.DECLARACION) && h2.tag.equals(TagAbstract.NULO)) { // declaracion de lista de variables sin asignacion
        if (tree.valor.equals("Boolean")) {
          for (Nodo var : h1.hijos) {
            addRow(new RowMip(TokenMip.ASSIGN, "FALSE", var.valor));
          }
        } else if (tree.valor.equals("INTEGER")) {
          for (Nodo var : h1.hijos) {
            addRow(new RowMip(TokenMip.ASSIGN, "0", var.valor));
          }
        } else if (h1.tag.equals(TagAbstract.DECLARACION) && h2.tag.equals(TagAbstract.ASSIGN)) { // declaracion de lista de variables con asignacion
          for (Nodo var : h1.hijos) {
            addRow(new RowMip(TokenMip.ASSIGN, h2.valor, var.valor));
          }
        }
      }
    } else if (tree.tag.equals(TagAbstract.CUERPO)) {
      addRow(new RowMip(TokenMip.ETIQ, TokenMip.MAIN));
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
    }
  }

  public void printCode () {
    for (RowMip row : code) {
      System.out.println(row.toString());
    }
  }

}