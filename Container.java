import Nodos.*;
import Tabla.*;

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

  public void addAll(Container con){
    this.tablaMain.addAll(con.tablaMain);
  }

  public void addSymbols(Container con){
    this.tablaMain.addSymbols(con.tablaMain);
  }

  public void addChildren(Container con){
    this.tablaMain.addChildren(con.tablaMain);
  }

  public void addChild(Container con){
    this.tablaMain.addChild(con.tablaMain);
  }

  public void addSymbol(Simbolo sim){
    this.tablaMain.addSymbol(sim);
  }

  public void addSymbolFirst(Simbolo sim){
    this.tablaMain.addSymbolFirst(sim);
  }

  public void addBrother(Container con){
    if(con.tablaMain.parent == null){
      con.tablaMain.parent = new TablaSimbolos();
    }
    con.tablaMain.parent.addChild(this.tablaMain);
  }

  public void adoptChildren(Container con){
    this.tablaMain.addChildren(con.tablaMain);
  }
}