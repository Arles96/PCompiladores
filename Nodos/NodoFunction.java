package Nodos;

import java.util.ArrayList;

class NodoFunction extends Nodo {

  public ArrayList<NodoId> parametros = new ArrayList<NodoId>();

  public ArrayList<NodoId> declaraciones = new ArrayList<NodoId>();

  public Nodo returnFunction;

  public NodoFunction () {}

  public NodoFunction (String tag, String valor) {
    super(tag, valor);
  }

}