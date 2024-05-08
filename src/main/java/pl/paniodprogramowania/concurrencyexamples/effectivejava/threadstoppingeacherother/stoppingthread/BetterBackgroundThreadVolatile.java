package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.stoppingthread;

import java.util.concurrent.TimeUnit;

public class BetterBackgroundThreadVolatile {
  // volatile sprawia, że dane się NIE cachują a wątki będą zawsze widzieć najnowszy wynik
  private static volatile boolean stopped = false;

  public static boolean isStopped() {
    return stopped;
  }

  public static void setStopped(boolean stopped) {
    BetterBackgroundThreadVolatile.stopped = stopped;
    System.out.println("stopped");
  }

  public static void main(String[] args) throws InterruptedException {
    Thread backgroundThread = new Thread( () -> {
      int i = 0;
      while (!isStopped()){
        i++;
      }
    });
    backgroundThread.start();
    TimeUnit.SECONDS.sleep(3);
    setStopped(true);
  }
}
