package Intermedio;

public class RowMip {

  private String token;
  private String value1;
  private String value2;
  private String result;

  public RowMip () {}

  public RowMip (String token, String value1, String value2, String result) {
    this.token = token;
    this.value1 = value1;
    this.value2 = value2;
    this.result = result;
  }

  public RowMip (String token, String result) {
    this.token = token;
    this.result = result;
    this.value1 = "";
    this.value2 = "";
  }

  // Getter y Setter

  public void setToken(String token) {
    this.token = token;
  }

  public void setValue1(String value1) {
    this.value1 = value1;
  }

  public void setValue2 (String value2) {
    this.value2 = value2;
  }

  public void setResult (String result) {
    this.result = result;
  }

  public String getToken () {
    return this.token;
  }

  public String getValue1 () {
    return this.value1;
  }

  public String getValue2 () {
    return this.value2;
  }

  public String getResult () {
    return this.result;
  }

}