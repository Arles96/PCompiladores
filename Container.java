import Nodos.*;

class Container {

  public Nodo nodo;
  public TablaSimbolos tablaMain;

  public Container () {}

  public Container (Nodo nodo, TablaSimbolos tablaMain) {
    this.nodo = nodo;
    this.tablaMain = tablaMain;
  }

  public Container (Nodo nodo) {
    this.nodo = nodo;
    this.tablaMain = new TablaSimbolos();
  }

  public Container (TablaSimbolos tablaMain) {
    this.tablaMain = tablaMain;
  }

}