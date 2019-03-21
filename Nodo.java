import java.util.ArrayList;

public class Nodo{
    
    public String tag;
    public String valor;
    public Nodo padre;
    public ArrayList<Nodo>hijos = new ArrayList<Nodo>();

    public Nodo(){

    }
    public Nodo(String tag,String valor){
        this.tag = tag;
        this.valor = valor;
    }
    public void addHijo(Nodo n){
        hijos.add(n);
    }
    
    public void setTag(String s){
        this.tag = s;
    }
    public void setValor(String s){
        this.valor = s;
    }

    public String getInfo(){
        return this.tag;
    }
}
