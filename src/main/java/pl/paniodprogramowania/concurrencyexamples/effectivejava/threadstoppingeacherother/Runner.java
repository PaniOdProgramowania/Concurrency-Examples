package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother;

import java.util.concurrent.TimeUnit;

public class Runner {
  public static void main(String[] args) throws InterruptedException {
    BackgroundThread backgroundThread = new BackgroundThread();

    backgroundThread.start();
    TimeUnit.SECONDS.sleep(3);
    BackgroundThread.setStopped(true);
  }
}
