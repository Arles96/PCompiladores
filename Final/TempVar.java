package Final;

public class TempVar {

  private String temp;
  private String var;
  private boolean empty;

  public TempVar () {
    empty = true;
  }

  public TempVar (String temp) {
    this.temp = temp;
    this.empty = true;
  }

  public TempVar (String temp, String var) {
    this.temp = temp;
    this.var = var;
    this.empty = false;
  }

  // getter and setter

  public void setTemp (String temp) {
    this.temp = temp;
  }

  public void setVar (String var) {
    this.var = var;
  }

  public void setEmpty (boolean empty) {
    this.empty = empty;
  }

  public String getTemp () {
    return this.temp;
  }

  public String getVar () {
    return this.var;
  }

  public boolean getEmpty () {
    return this.empty;
  }

  // funcion para limpiar el temporal
  public void clearVar () {
    this.var = null;
    this.empty = true;
  }

  // funcion para llenar una temporal
  public void addVar (String var) {
    this.var = var;
    this.empty = false;
  }

}