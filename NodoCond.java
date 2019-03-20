
class NodoCond extends Nodo{
    String operador;
    Nodo operacionDerecha;
    Nodo operacionIzquierda;
    String tipoCondicionDerecha = "";
    Nodo condicionDerecha;

    public NodoCond(String operador, Nodo operacionDerecha, Nodo operacionIzquierda, String tipoCondicionDerecha, Nodo condicionDerecha,String label, ArrayList<Node>children){
        super(label,children);
        this.operador = operador;
        this.operacionDerecha = operacionDerecha;
        this.operacionIzquierda = operacionIzquierda;
        this.tipoCondicionDerecha = tipoCondicionDerecha;
        this.condicionDerecha = condicionDerecha;

    }
}