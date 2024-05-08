package pl.paniodprogramowania.concurrencyexamples.raceconditions;

public class SumNumbrsWithGettersAndSettersNoSync extends Thread{
  private int i = 0;

  @Override
  public void run() {
    setI(getI() + 1);
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }
}
