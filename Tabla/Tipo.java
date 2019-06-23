package Tabla;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Dario Mendoza
 */
public abstract class Tipo {
    public String nombre;
    public int size;
    public LinkedList<Simbolo> composicion;

    public Tipo() {
        this.nombre = "";
        this.size = 0;
        this.composicion = new LinkedList();
    }

    public Tipo(String nombre, int size) {
        this.nombre = nombre;
        this.size = size;
        this.composicion = new LinkedList();
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

    public LinkedList<Simbolo> getComposicion() {
        return composicion;
    }

    public void setComposicion(LinkedList<Simbolo> composicion) {
        this.composicion = composicion;
    }

    public void addComposicion(Simbolo composicion) {
        this.composicion.add(composicion);
    }

    public String toString(){
        return this.nombre;
    }
}
