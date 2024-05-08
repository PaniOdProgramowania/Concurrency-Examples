package pl.paniodprogramowania.concurrencyexamples.raceconditions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Runner {
  public static void main(String[] args) throws InterruptedException {
    withSync();
  }

  private static void noGettersNoSettersNoSync() throws InterruptedException {
    SumNumbersNoGettersSetters task;
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      task = new SumNumbersNoGettersSetters();

      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    System.out.println(task.getI());
  }

  private static void withGettersNoSettersNoSync() throws InterruptedException {
    SumNumbrsWithGettersAndSettersNoSync task;
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      task = new SumNumbrsWithGettersAndSettersNoSync();

      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    System.out.println(task.getI());
  }

  private static void withSync() throws InterruptedException {
    SumNumbersWithSyn task;
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      task = new SumNumbersWithSyn();

      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    System.out.println(task.getI());
  }
}
