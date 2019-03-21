public class AdaError {

  private String token, tipo, descripcion;
  private int columna, fila;

  public AdaError (String token, String tipo, String descripcion) {
    this.token = token;
    this.tipo = tipo;
    this.descripcion = descripcion;
  }

}