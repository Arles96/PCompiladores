
import java.util.ArrayList;

/**
 *
 * @author Dario Mendoza
 */
public class TablaSimbolos {
    public TablaSimbolos parent;
    public ArrayList<TablaSimbolos> children;
    public ArrayList<Simbolo> tabla;

    public TablaSimbolos() {
        this.parent = null;
        this.children = new ArrayList();
        this.tabla = new ArrayList();
    }
    
    public TablaSimbolos(TablaSimbolos padre) {
        this.parent = padre;
        this.children = new ArrayList();
        this.tabla = new ArrayList();
    }

    public TablaSimbolos(TablaSimbolos padre, ArrayList<TablaSimbolos> hijos) {
        this.parent = padre;
        this.children = hijos;
        this.tabla = new ArrayList();
    }
    
    public void addChild(TablaSimbolos child){
        child.parent = this;
        this.children.add(child);
    }
}
