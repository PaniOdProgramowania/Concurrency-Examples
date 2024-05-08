package pl.paniodprogramowania.concurrencyexamples.effectivejava.deadlock.crossroad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crossroad {
  private List<Car> carsFromAheadThatAreTurningLeft = new ArrayList<>();
  private List<Car> carsTurningRight = new ArrayList<>();
  private final int maxCars = 2;

  // błędne
  public synchronized void addCarTurningLeft(Car car) {
    System.out.println(Thread.currentThread() + " turning left");
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      service.submit(() -> {
        while (carsTurningRight.size() >= 5) {
          this.removeCarTurningRight(carsTurningRight.get(0));
        }
      });
      service.close();
    }
    carsFromAheadThatAreTurningLeft.add(car);
  }

  public synchronized void addCarTurningRight(Car car) {
    System.out.println(Thread.currentThread() + " turning right");
    try (ExecutorService service = Executors.newFixedThreadPool(3)) {
      service.submit(() -> {
        while (carsFromAheadThatAreTurningLeft.size() >= 5) {
          this.removeCarTurningLeft(carsFromAheadThatAreTurningLeft.get(0));
        }
      });
      service.close();
    }
    carsTurningRight.add(car);
  }

  public synchronized void removeCarTurningLeft(Car car) {
    carsFromAheadThatAreTurningLeft.remove(car);
  }

  public synchronized void removeCarTurningRight(Car car) {
    carsTurningRight.remove(car);
  }

  public List<Car> getCarsFromAheadThatAreTurningLeft() {
    return carsFromAheadThatAreTurningLeft;
  }

  public List<Car> getCarsTurningRight() {
    return carsTurningRight;
  }

  public int getMaxCars() {
    return maxCars;
  }
}
