package pl.paniodprogramowania.concurrencyexamples.effectivejava.deadlock.crossroad;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pl.paniodprogramowania.concurrencyexamples.raceconditions.SumNumbersWithSyn;

public class Runner {
  public static void main(String[] args) throws InterruptedException {
    Crossroad crossroad;
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      crossroad = new Crossroad();

      for (int i = 0; i < 10000; i++) {
        Car car = new Car(UUID.randomUUID().toString().substring(0, 6));
        service.submit(() -> crossroad.addCarTurningLeft(car));

        Car car2 = new Car(UUID.randomUUID().toString().substring(0, 6));
        service.submit(() -> crossroad.addCarTurningRight(car2));

      }

      service.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    System.out.println(crossroad.getCarsTurningRight());
    System.out.println(crossroad.getCarsFromAheadThatAreTurningLeft());

  }
}
