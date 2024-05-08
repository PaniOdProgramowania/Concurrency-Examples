package pl.paniodprogramowania.concurrencyexamples.effectivejava.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CorrectedMainThread {

  private List<String> valueableList = new ArrayList<>();

  public void addToList(String newValue) {
    synchronized (valueableList) {
      valueableList.add(newValue);
      valueableList.add("Necklace" + newValue);
    }
    System.out.println(Thread.currentThread() + "added " + newValue + " and Necklace" + newValue);

    Thread deleteJewelsThread = new Thread(() -> {
      System.out.println(Thread.currentThread() + "deleting necklace...");
      this.deleteFromList("necklace");
      System.out.println("..." + Thread.currentThread() + "deleted necklace");
    });
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    // synchronised sprawiło, że jest zablokowany już "this" a w tym threadzie użylibysmy
    // deleteFromList, co próbuje nałożyć locka ale juz nie ma jak bo jesy założony
    executorService.submit(deleteJewelsThread);
    try {
      executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    executorService.close();
  }

  public synchronized void deleteFromList(String valueToBeRemoved) {
    valueableList.remove(valueToBeRemoved);
  }

  public List<String> getValueableList() {
    return valueableList;
  }
}
