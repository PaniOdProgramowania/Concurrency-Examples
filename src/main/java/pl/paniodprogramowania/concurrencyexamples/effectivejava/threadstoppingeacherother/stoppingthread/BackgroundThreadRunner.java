package pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.stoppingthread;

import java.util.concurrent.TimeUnit;

// nie kończy się nigdy z powodu zmiany kodu źródłowego przez jvm
public class BackgroundThreadRunner {
  private static boolean stopped = false;
  public static void main(String[] args) throws InterruptedException {
    Thread backgroundThread = new Thread( () -> {
      int i = 0;
      while (!stopped){
        i++;
      }
    });
    backgroundThread.start();
    TimeUnit.SECONDS.sleep(3);
    stopped = true; // background thread może nie zobaczyć zmiany tej wartości - jvm może
    // "zoptymalizować" kod do takiej postacji:
    // if (!stopped) {
    //   while (true) { .... } }
    // i do ifa już nie wróci
  }

  // zdekompilowany byte code:
  /*
    public static void main(String[] args) throws InterruptedException {
    Thread backgroundThread = new Thread(() -> {
      for(int i = 0; !stopped; ++i) { ----> tu się zatrzymujemy w pętli nieskończonej! nie widzi
      Java zmiany stanu, nie nasłuchuje na to
      }

    });
    backgroundThread.start();
    TimeUnit.SECONDS.sleep(3L);
    stopped = true;
  }
   */
}
