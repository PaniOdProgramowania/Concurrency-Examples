package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.stoppingthread;

import java.util.concurrent.TimeUnit;

public class BetterBackgroundThreadRunner {
  private static boolean stopped = false;

  // należy synchronizować odczyt oraz zapis zawsze razem
  public static synchronized boolean isStopped() {
    return stopped;
  }

  public static synchronized void setStopped(boolean stopped) {
    BetterBackgroundThreadRunner.stopped = stopped;
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

  /*
  zdekompilowany kod:
    private static boolean stopped = false;

    public BetterBackgroundThreadRunner() {
    }

    public static synchronized boolean isStopped() {
      return stopped;
    }

    public static synchronized void setStopped(boolean stopped) {
      BetterBackgroundThreadRunner.stopped = stopped;
      System.out.println("stopped");
    }

    public static void main(String[] args) throws InterruptedException {
      Thread backgroundThread = new Thread(() -> {
        for(int i = 0; !isStopped(); ++i) { // ----> tutaj Java używa METODY, którą możemy
        synchronizować a zatem nią sterować
        }

      });
      backgroundThread.start();
      TimeUnit.SECONDS.sleep(3L);
      setStopped(true);
  }
   */
}
