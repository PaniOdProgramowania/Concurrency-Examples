package pl.paniodprogramowania.concurrencyexamples.raceconditions;

public class SumNumbersNoGettersSetters extends Thread {
  private int i = 0;

  @Override
  public void run() {
    i++;
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }
}
