// : concurrency/Chopstick.java
package concurrency; /* Added by Eclipse.py */

// Chopsticks for dining philosophers.

public class Chopstick {
    private boolean taken = false;

    // 筷子被拿走
    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }

    // 筷子被放回
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
} /// :~
