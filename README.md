# Java Multithreading — Real World Examples

A small Java project that explains core multithreading concepts using
everyday, relatable scenarios instead of abstract theory.

## What's inside (`src/`)

| File | Real-world scenario | Concept demonstrated |
|---|---|---|
| `RestaurantKitchenDemo.java` | 3 chefs cooking different dishes at once | Basic `Thread` creation & parallel execution |
| `BankAccountDemo.java` | Husband & wife withdrawing from a joint account | Race conditions & the `synchronized` keyword |
| `CoffeeShopDemo.java` | Baristas making coffee, customers drinking it | Producer-Consumer pattern with `BlockingQueue` |
| `TicketBookingDemo.java` | 10 users booking 5 movie seats | `ExecutorService` (thread pools) & `AtomicInteger` |
| `Main.java` | Menu to run any/all of the above | Entry point |

## How to run

You need a JDK installed (Java 11+ recommended).

```bash
cd src
javac *.java
java Main
```

You'll be prompted to pick a demo (1-4), or choose `5` to run all of
them back-to-back. If you just want to run everything without typing
anything, pipe input in:

```bash
echo "5" | java Main
```

## Why these examples?

- **Restaurant Kitchen** shows the *speed benefit* of threads — tasks
  that would normally run one after another instead run side by side.
- **Bank Account** shows the *danger* of multithreading (a race
  condition) and how `synchronized` fixes it, using a scenario everyone
  understands: money.
- **Coffee Shop** shows how producers and consumers can safely hand off
  work through a shared, bounded queue without manual lock management.
- **Ticket Booking** shows how real systems (like ticket or e-commerce
  sites) avoid creating a new thread per user by reusing a fixed pool
  of worker threads, and how `AtomicInteger` keeps a shared counter
  consistent without explicit locks.

Feel free to tweak the numbers (cooking times, seat counts, queue
capacity) and re-run to see how the behavior changes.
