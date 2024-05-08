package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.inthebook.counter;

import java.util.concurrent.atomic.AtomicLong;

public class BestCounter {
  private static AtomicLong id = new AtomicLong(0L);

  public static long nextId() {
    return id.addAndGet(1L);
  }
}
