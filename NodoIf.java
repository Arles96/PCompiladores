import java.util.ArrayList;

class NodoIf extends Nodo{
    
    public ArrayList<Nodo> condicionales;

    public NodoIf(String label, ArrayList<Node>children, ArrayList<Nodo>condicionales){
        super(label,children);
        this.condicionales = condicionales;

    }
    public void setCodicionales(){
        Nodo actual = this.condicionales.get(0);
        while(actual != null){
            actual = ((NodoCond)actual).condicionDerecha;
            this.condicionales.add(actual);
        }
    }
    
}