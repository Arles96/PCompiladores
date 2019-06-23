package Final;

import java.util.LinkedList;

import Intermedio.Mips;
import Intermedio.RowMip;

public class Binary {

  public LinkedList<String> lineCode = new LinkedList<String>();
  private Mips mips;

  // constructores

  public Binary () {}

  public Binary (Mips mips) {
    this.mips = mips;
  }

  // getters and setters

  public void setMips (Mips mips) {
    this.mips = mips;
  }

  public Mips getMips () {
    return mips;
  }

  private void addLine (String line) {
    lineCode.add(line);
  }

  public void generateCode () {
    addLine(".data");
    for (RowMip line : mips.codeString) {

    }
  }

  public void saveCodeFile () {

  }

}