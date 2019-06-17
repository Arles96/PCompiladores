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

  public void incrementEt () {
    counterEt++;
  }

  public int getCounterVar () {
    return this.counterVar;
  }

  public int getCounterEt () {
    return this.counterEt;
  }

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
    }
  }

  public void printCode () {
    for (RowMip row : code) {
      System.out.println(row.toString());
    }
  }

}