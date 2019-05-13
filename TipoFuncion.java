
import java.util.ArrayList;

/**
 *
 * @author Dario Mendoza
 */
public class TipoFuncion extends Tipo{
    public Tipo rango;
    
    public TipoFuncion(){
        this.rango = null;
        this.size = 0;
        this.nombre = "function";
    }

    public TipoFuncion(Tipo rango){
        super();
        this.rango = rango;
        this.size = 0;
        this.nombre = "function";
    }

    public TipoFuncion(Tipo rango, ArrayList<Tipo> composicion){
        this.composicion = composicion;
        this.rango = rango;
        //this.width=range.width;
        this.size = 0;
        this.nombre = "function";
    }

    public TipoFuncion(ArrayList<Tipo> composicion){
        this.composicion = composicion;
        this.size = 0;
        this.rango = null;//Should I create a void class?
        this.nombre = "function";
    }
}
