package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.inthebook.counter;

public class BetterCounter {
  // zmiany to dodanie synchronised oraz usunięcie volatile
  private static  int counter = 0;

  public static synchronized int nextId() {
    return counter++;
  }

}
