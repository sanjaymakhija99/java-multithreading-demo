import java.util.Scanner;

/**
 * MAIN MENU
 * ---------
 * This program demonstrates Java Multithreading using 4 real-world examples:
 *
 * 1. Restaurant Kitchen   -> Basic Thread creation (multiple chefs cooking in parallel)
 * 2. Bank Account         -> Synchronization (multiple people withdrawing from a shared account)
 * 3. Coffee Shop          -> Producer-Consumer pattern (baristas making, customers consuming)
 * 4. Movie Ticket Booking -> ExecutorService / Thread Pool (multiple users booking seats)
 *
 * Run each demo to see how multithreading behaves, why problems occur
 * without proper synchronization, and how Java solves them.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println(" JAVA MULTITHREADING - REAL WORLD EXAMPLES");
        System.out.println("=================================================");
        System.out.println("1. Restaurant Kitchen   (Basic Threads)");
        System.out.println("2. Bank Account         (Synchronization)");
        System.out.println("3. Coffee Shop          (Producer-Consumer)");
        System.out.println("4. Ticket Booking       (Thread Pool / ExecutorService)");
        System.out.println("5. Run ALL demos one after another");
        System.out.println("-------------------------------------------------");
        System.out.print("Enter your choice (1-5): ");

        int choice = 5; // default when no input is available (e.g. piped run)
        try {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("(No input detected, running ALL demos by default)");
            }
        } catch (Exception e) {
            System.out.println("(Invalid input, running ALL demos by default)");
        }

        switch (choice) {
            case 1:
                RestaurantKitchenDemo.run();
                break;
            case 2:
                BankAccountDemo.run();
                break;
            case 3:
                CoffeeShopDemo.run();
                break;
            case 4:
                TicketBookingDemo.run();
                break;
            default:
                RestaurantKitchenDemo.run();
                pause();
                BankAccountDemo.run();
                pause();
                CoffeeShopDemo.run();
                pause();
                TicketBookingDemo.run();
        }

        System.out.println("\nAll demos finished. Thanks for exploring Java Multithreading!");
    }

    private static void pause() throws InterruptedException {
        System.out.println("\n-------------------------------------------------\n");
        Thread.sleep(300);
    }
}
