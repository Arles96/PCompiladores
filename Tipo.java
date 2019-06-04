import java.util.ArrayList;

/**
 *
 * @author Dario Mendoza
 */
public abstract class Tipo {
    public String nombre;
    public int size;
    public ArrayList<Tipo> composicion;

    public Tipo() {
        this.nombre = "";
        this.size = 0;
        this.composicion = new ArrayList();
    }

    public Tipo(String nombre, int size) {
        this.nombre = nombre;
        this.size = size;
        this.composicion = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Tipo> getComposicion() {
        return composicion;
    }

    public void setComposicion(ArrayList<Tipo> composicion) {
        this.composicion = composicion;
    }
    
    public void addComposicion(Tipo composicion) {
        this.composicion.add(composicion);
    }

    public String toString(){
        return this.nombre;
    }
}
