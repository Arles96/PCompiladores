
/**
 *
 * @author Dario Mendoza
 */
public class Simbolo {
    public String id;
    public boolean esConstante;
    public Tipo tipo;
    public int offset;

    public Simbolo() {
    }
    
    public Simbolo(String id, boolean esConstante, Tipo tipo) {
        this.id = id;
        this.esConstante = esConstante;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEsConstante() {
        return esConstante;
    }

    public void setEsConstante(boolean esConstante) {
        this.esConstante = esConstante;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return id + ": " + ((tipo == null)?"null":tipo) + " offset: " + offset;
    }
}
