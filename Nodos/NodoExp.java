package Nodos;

class NodoExp extends Nodo {

  public String oprel;
  public NodoExp izquierda;
  public NodoExp derecha;

  public NodoExp (String tag, String valor) {
    super(tag, valor);
  }

  public NodoExp (String oprel) {
    super();
    this.oprel = oprel;
  }

  public NodoExp (String oprel, NodoExp izquierda, NodoExp derecha) {
    super();
    this.oprel = oprel;
    this.izquierda = izquierda;
    this.derecha = derecha;
  }

}