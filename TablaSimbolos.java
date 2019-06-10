
import java.util.LinkedList;

/**
 *
 * @author Dario Mendoza
 */
public class TablaSimbolos {
    public TablaSimbolos parent;
    public LinkedList<TablaSimbolos> children;
    public LinkedList<Simbolo> tabla;

    public TablaSimbolos() {
        this.parent = null;
        this.children = new LinkedList();
        this.tabla = new LinkedList();
    }
    
    public TablaSimbolos(TablaSimbolos padre) {
        this.parent = padre;
        this.children = new LinkedList();
        this.tabla = new LinkedList();
    }

    public TablaSimbolos(TablaSimbolos padre, LinkedList<TablaSimbolos> hijos) {
        this.parent = padre;
        this.children = hijos;
        this.tabla = new LinkedList();
    }
    
    public void addChild(TablaSimbolos child) { 
        child.parent = this;
        this.children.add(child);
    }

    public void addSymbol(Simbolo sym) { //TODO: agregar comprobacion
        this.tabla.addLast(sym);
    }

    public void addSymbolFirst(Simbolo sym) {
        this.tabla.addFirst(sym);
    }

    public void addAll(TablaSimbolos tab) {
        for(Simbolo sim: tab.tabla){
            this.addSymbol(sim);
        }

        this.children.addAll(tab.children);
    }

    public void addSymbols(TablaSimbolos tab){
        for(Simbolo sim: tab.tabla){
            this.addSymbol(sim);
        }
    }

    public void addChildren(TablaSimbolos tab){
        this.children.addAll(tab.children);
    }

    public boolean asignarTipo(String tipo) {
        for(int i = 0; i < tabla.size(); i++){
            if(tabla.get(i).tipo == null) {
                if(tipo.compareToIgnoreCase("integer") == 0){
                    tabla.get(i).tipo = new TipoInteger();
                }
                else if(tipo.compareToIgnoreCase("float") == 0)
                    tabla.get(i).tipo = new TipoFloat();
                /*else if(tipo.compareToIgnoreCase("double"))
                    tabla.get(i).tipo = new TipoDouble();*/
                else if(tipo.compareToIgnoreCase("boolean") == 0)
                    tabla.get(i).tipo = new TipoBoolean();
                else 
                    return false;
            }
        }
        return true;
    }
}
