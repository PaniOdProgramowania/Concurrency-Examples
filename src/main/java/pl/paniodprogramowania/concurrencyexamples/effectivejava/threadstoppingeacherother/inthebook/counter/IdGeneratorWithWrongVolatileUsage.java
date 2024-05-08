package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.inthebook.counter;

public class IdGeneratorWithWrongVolatileUsage {
  // z czy bez volatile - nie działa
  private static  int counter = 0;

  public static int nextId() {
    return counter++;
  }

}
