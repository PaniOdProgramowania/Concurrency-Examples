package pl.paniodprogramowania.concurrencyexamples.raceconditions;

public class SumNumbersWithSyn extends Thread{
  private static int i = 0;

  @Override
  public synchronized void run() {
    setI(getI() + 1);
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }
}
