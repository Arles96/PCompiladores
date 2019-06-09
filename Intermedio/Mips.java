package Intermedio;

import java.util.LinkedList;

public class Mips {

  public LinkedList <RowMip> rows = new LinkedList<RowMip>();
  private int counterVar = 0;
  private int counterEt = 0;

  public Mips () {}

  public void addRow (RowMip r) {
    rows.add(r);
  }

  public void incrementVar () {
    counterVar++;
  }

  public void incrementEt () {
    counterEt++;
  }

  public int getCounterVar () {
    return this.counterVar;
  }

  public int getCounterEt () {
    return this.counterEt;
  }

}