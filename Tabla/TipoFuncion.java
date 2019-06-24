package Tabla;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Dario Mendoza
 */
public class TipoFuncion extends Tipo{
    public Tipo rango;

    public TipoFuncion(){
        this.rango = null;
        this.size = 0;
        this.nombre = "procedure";
    }

    public TipoFuncion(Tipo rango){
        super();
        this.rango = rango;
        this.size = 0;
        this.nombre = "function";
    }

    public TipoFuncion(String rango){
        super();
        this.rango = null;
        this.size = 0;
        this.nombre = "function";

        if(rango.compareToIgnoreCase("integer") == 0)
            this.rango = new TipoInteger();
        else if(rango.compareToIgnoreCase("float") == 0)
            this.rango = new TipoFloat();
        else if(rango.compareToIgnoreCase("boolean") == 0)
            this.rango = new TipoBoolean();
        else if(rango.compareToIgnoreCase("0") == 0)
            this.nombre = "procedure";

    }

    public TipoFuncion(Tipo rango, LinkedList<Simbolo> composicion){
        this.composicion = composicion;
        this.rango = rango;
        //this.width=range.width;
        this.size = 0;
        this.nombre = "function";
    }

    public TipoFuncion(LinkedList<Simbolo> composicion){
        this.composicion = composicion;
        this.size = 0;
        this.rango = null;//Should I create a void class?
        this.nombre = "function";
    }

    public String toString(){
        return this.nombre + "(" + ((this.rango == null)?"":this.rango) + ")";
    }
}
