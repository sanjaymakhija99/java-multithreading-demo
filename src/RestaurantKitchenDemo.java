/**
 * REAL WORLD EXAMPLE 1: Restaurant Kitchen
 * -----------------------------------------
 * Concept demonstrated: Basic Thread creation & parallel execution.
 *
 * Imagine a restaurant kitchen with 3 chefs. Instead of cooking dishes
 * one after another (sequentially), each chef works on their own dish
 * AT THE SAME TIME. This is exactly what multithreading allows a CPU
 * to do - run multiple tasks concurrently instead of waiting for each
 * one to finish before starting the next.
 */
public class RestaurantKitchenDemo {

    static class Chef extends Thread {
        private final String chefName;
        private final String dish;
        private final int cookingTimeMs;

        Chef(String chefName, String dish, int cookingTimeMs) {
            this.chefName = chefName;
            this.dish = dish;
            this.cookingTimeMs = cookingTimeMs;
        }

        @Override
        public void run() {
            System.out.println("[" + chefName + "] started cooking " + dish);
            try {
                Thread.sleep(cookingTimeMs); // simulates time taken to cook
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + chefName + "] finished cooking " + dish
                    + " (took " + cookingTimeMs + "ms)");
        }
    }

    public static void run() throws InterruptedException {
        System.out.println(">>> DEMO 1: Restaurant Kitchen (Basic Threads)");
        System.out.println("Without threads, 3 dishes taking 2s, 3s, 1.5s would take 6.5s total.");
        System.out.println("With threads, they cook in parallel - total time ~= slowest dish.\n");

        long start = System.currentTimeMillis();

        Chef chef1 = new Chef("Chef Alice", "Pasta", 2000);
        Chef chef2 = new Chef("Chef Bob", "Steak", 3000);
        Chef chef3 = new Chef("Chef Carol", "Soup", 1500);

        // Starting threads - each chef begins cooking independently
        chef1.start();
        chef2.start();
        chef3.start();

        // join() waits for all chefs to finish before the kitchen "closes"
        chef1.join();
        chef2.join();
        chef3.join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nAll dishes are ready! Total kitchen time: " + elapsed + "ms");
        System.out.println("(Notice it's close to 3000ms, NOT 6500ms - that's the power of threads)");
    }
}
