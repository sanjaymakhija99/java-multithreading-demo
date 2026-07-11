import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * REAL WORLD EXAMPLE 3: Coffee Shop
 * ----------------------------------
 * Concept demonstrated: Producer-Consumer pattern using BlockingQueue.
 *
 * Baristas (producers) make coffee cups and place them on a counter
 * (a shared queue) that can only hold a limited number of cups at once.
 * Customers (consumers) pick up cups from the counter and drink them.
 * If the counter is full, baristas must wait. If the counter is empty,
 * customers must wait. BlockingQueue handles all this waiting safely
 * without us writing manual locks.
 */
public class CoffeeShopDemo {

    public static void run() throws InterruptedException {
        System.out.println(">>> DEMO 3: Coffee Shop (Producer-Consumer)");
        System.out.println("Counter can hold max 3 cups. 2 baristas produce, 2 customers consume.\n");

        // Shared counter with capacity of 3 cups
        BlockingQueue<String> counter = new LinkedBlockingQueue<>(3);
        final int cupsPerBarista = 4;

        Runnable barista = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= cupsPerBarista; i++) {
                String cup = name + "-Cup" + i;
                try {
                    counter.put(cup); // waits if counter is full
                    System.out.println("[" + name + "] placed " + cup + " on counter (counter size=" + counter.size() + ")");
                    Thread.sleep(200); // time to brew next cup
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable customer = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= cupsPerBarista; i++) {
                try {
                    String cup = counter.take(); // waits if counter is empty
                    System.out.println("    [" + name + "] picked up " + cup + " and is drinking it");
                    Thread.sleep(300); // time to drink
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread barista1 = new Thread(barista, "Barista-Ana");
        Thread barista2 = new Thread(barista, "Barista-Ravi");
        Thread customer1 = new Thread(customer, "Customer-John");
        Thread customer2 = new Thread(customer, "Customer-Mei");

        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();

        barista1.join();
        barista2.join();
        customer1.join();
        customer2.join();

        System.out.println("\nCoffee shop closed for the day. All cups made were also consumed.");
        System.out.println("BlockingQueue automatically handled waiting - no manual locks needed!");
    }
}
