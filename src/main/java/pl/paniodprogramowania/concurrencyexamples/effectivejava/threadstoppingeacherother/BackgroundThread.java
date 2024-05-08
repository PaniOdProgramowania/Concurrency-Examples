package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother;

public class BackgroundThread extends Thread {
  private static boolean stopped;

  @Override
  public synchronized void run() {
    int counter = 0;
    while (!stopped) {
      counter++;
      System.out.println(counter);
    }
  }

  public static void setStopped(boolean stopped) {
    BackgroundThread.stopped = stopped;
  }
}
