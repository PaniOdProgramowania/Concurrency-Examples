package pl.paniodprogramowania.concurrencyexamples.effectivejava.deadlock.crossroad;

public class Car {
  private String plate;

  public Car(String plate) {
    this.plate = plate;
  }

  @Override
  public String toString() {
    return "Car{" +
        "plate='" + plate + '\'' +
        '}';
  }
}
