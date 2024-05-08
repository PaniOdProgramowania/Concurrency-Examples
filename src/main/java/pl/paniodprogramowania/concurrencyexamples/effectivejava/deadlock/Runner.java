package pl.paniodprogramowania.concurrencyexamples.effectivejava.deadlock;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pl.paniodprogramowania.concurrencyexamples.effectivejava.threadstoppingeacherother.BackgroundThread;

public class Runner {

  public static void main(String[] args) throws InterruptedException {
//    MainThread mainThread = new MainThread();
    CorrectedMainThread mainThread = new CorrectedMainThread();

    List<String> jewels = List.of(
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace",
        "Gold", "Silver", "Platinum", "Diamond", "necklace"
        );

    try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
      for (String jewel : jewels) {
        executorService.submit(() -> mainThread.addToList(jewel));
      }


      executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
      executorService.close();
      System.out.println("koniec");
    }
    System.out.println(mainThread.getValueableList());


  }
}
