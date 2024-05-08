package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.inthebook.counter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pl.paniodprogramowania.concurrencyexamples.raceconditions.SumNumbrsWithGettersAndSettersNoSync;

public class Runner {
  public static void main(String[] args) throws InterruptedException {
    usageOfBestWay();
  }

  private static void usageOfBadWay() throws InterruptedException {
    Set<Integer> numbers = new HashSet<>();
    // bez volatile w IdGeneratorze -> wypiszą się duplikaty bo wątki będą czytać szerowany
    // zasób błędnie

    Thread task = new Thread( () -> {
      int id = IdGeneratorWithWrongVolatileUsage.nextId();
      // synchronised aby zminimalizowac szanse ze to tu błędnie będzie działac
      synchronized (Runner.class) {
        var result = numbers.add(id);
        if (!result) {
          System.out.println("duplicate");
        }
      }
    });

    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
      System.out.println(numbers.size());
    }
  }

  private static void usageOfGoodWay() throws InterruptedException {
    Set<Integer> numbers = new HashSet<>();
    // bez volatile w IdGeneratorze -> wypiszą się duplikaty bo wątki będą czytać szerowany
    // zasób błędnie

    Thread task = new Thread( () -> {
      int id = BetterCounter.nextId();
      // synchronised aby zminimalizowac szanse ze to tu błędnie będzie działac
      synchronized (Runner.class) {
        var result = numbers.add(id);
        if (!result) {
          System.out.println("duplicate");
        }
      }
    });

    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
      System.out.println(numbers.size());
    }
  }

  private static void usageOfBestWay() throws InterruptedException {
    Set<Long> numbers = new HashSet<>();
    // bez volatile w IdGeneratorze -> wypiszą się duplikaty bo wątki będą czytać szerowany
    // zasób błędnie

    Thread task = new Thread( () -> {
      long id = BestCounter.nextId();
      // synchronised aby zminimalizowac szanse ze to tu błędnie będzie działac
      synchronized (Runner.class) {
        var result = numbers.add(id);
        if (!result) {
          System.out.println("duplicate");
        }
      }
    });

    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      for (int i = 0; i < 1000; i++) {
        service.submit(task);
      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
      System.out.println(numbers.size());
    }
  }
}



