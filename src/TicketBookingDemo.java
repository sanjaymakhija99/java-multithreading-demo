import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * REAL WORLD EXAMPLE 4: Movie Ticket Booking
 * --------------------------------------------
 * Concept demonstrated: ExecutorService (Thread Pool) + AtomicInteger.
 *
 * A popular movie has only 5 seats left, but 10 people try to book
 * tickets online at the same time. Instead of creating a brand-new
 * Thread object for every single user (which is expensive and wasteful),
 * we use a THREAD POOL (ExecutorService) that reuses a fixed number of
 * worker threads to handle many tasks efficiently - just like a booking
 * website handles thousands of users with a limited server capacity.
 *
 * AtomicInteger is used instead of a plain int to safely count remaining
 * seats across multiple threads without needing explicit synchronization.
 */
public class TicketBookingDemo {

    public static void run() throws InterruptedException {
        System.out.println(">>> DEMO 4: Movie Ticket Booking (ExecutorService / Thread Pool)");
        System.out.println("5 seats available. 10 users try to book at the same time.\n");

        AtomicInteger seatsAvailable = new AtomicInteger(5);

        // A pool of 4 worker threads handles all 10 booking requests
        ExecutorService bookingPool = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 10; i++) {
            final int userId = i;
            bookingPool.submit(() -> bookSeat(userId, seatsAvailable));
        }

        bookingPool.shutdown(); // no new tasks accepted
        bookingPool.awaitTermination(10, TimeUnit.SECONDS); // wait for all tasks to complete

        System.out.println("\nBooking window closed. Seats remaining: " + seatsAvailable.get());
        System.out.println("Only 4 worker threads handled 10 users - efficient reuse instead of");
        System.out.println("creating 10 separate Thread objects. This is how real booking systems scale.");
    }

    private static void bookSeat(int userId, AtomicInteger seatsAvailable) {
        String worker = Thread.currentThread().getName();
        System.out.println("User-" + userId + " (handled by " + worker + ") is trying to book a seat...");

        // decrementAndGet is atomic - safe even with multiple threads calling it at once
        int remaining = seatsAvailable.decrementAndGet();

        if (remaining >= 0) {
            System.out.println("  --> SUCCESS: User-" + userId + " booked a seat! Seats left: " + remaining);
        } else {
            // Correct the count back since this booking wasn't valid
            seatsAvailable.incrementAndGet();
            System.out.println("  --> SOLD OUT: User-" + userId + " could not book a seat.");
        }
    }
}
