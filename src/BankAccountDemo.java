/**
 * REAL WORLD EXAMPLE 2: Bank Account
 * -----------------------------------
 * Concept demonstrated: Race conditions & synchronization.
 *
 * Imagine a shared joint bank account. If a husband and wife both try
 * to withdraw money AT THE EXACT SAME TIME from an ATM, and the program
 * doesn't protect the balance properly, both withdrawals might read the
 * same starting balance and the bank could lose track of money
 * (a "race condition"). The 'synchronized' keyword locks the account so
 * only ONE thread can withdraw at a time, keeping the balance correct.
 */
public class BankAccountDemo {

    static class BankAccount {
        private int balance;

        BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }

        // NOT synchronized - unsafe version (kept for explanation only, not used below)
        void unsafeWithdraw(String person, int amount) {
            if (balance >= amount) {
                System.out.println(person + " sees balance: " + balance + ", withdrawing " + amount);
                try {
                    Thread.sleep(50); // simulate processing delay - this is where race conditions happen
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                balance -= amount;
                System.out.println(person + " completed withdrawal. New balance: " + balance);
            } else {
                System.out.println(person + " FAILED withdrawal of " + amount + " - insufficient funds");
            }
        }

        // synchronized - safe version, only one thread can execute this at a time per object
        synchronized void safeWithdraw(String person, int amount) {
            if (balance >= amount) {
                System.out.println(person + " sees balance: " + balance + ", withdrawing " + amount);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                balance -= amount;
                System.out.println(person + " completed withdrawal. New balance: " + balance);
            } else {
                System.out.println(person + " FAILED withdrawal of " + amount + " - insufficient funds");
            }
        }

        int getBalance() {
            return balance;
        }
    }

    public static void run() throws InterruptedException {
        System.out.println(">>> DEMO 2: Bank Account (Synchronization)");
        System.out.println("A joint account with $1000. Husband and Wife both try to withdraw $700");
        System.out.println("at almost the same time, from two different ATMs (two threads).\n");

        BankAccount account = new BankAccount(1000);

        Thread husband = new Thread(() -> account.safeWithdraw("Husband", 700));
        Thread wife = new Thread(() -> account.safeWithdraw("Wife", 700));

        husband.start();
        wife.start();

        husband.join();
        wife.join();



        System.out.println("\nFinal balance: " + account.getBalance());
        System.out.println("Because withdraw() is 'synchronized', the second person always waits");
        System.out.println("their turn and sees the UPDATED balance - preventing overdrawing the account.");



    }
}
