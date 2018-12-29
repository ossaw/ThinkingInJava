package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenerator2 extends IntGenerator2 {
    private int val;
    private Lock lock = new ReentrantLock();

    @Override
    int next() {
        lock.lock();
        try {
            val++;
            Thread.yield();
            val++;
            // return语句必须在所的范围之内，否则将数据过早暴露给其他线程。
            return val;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker2.test(new MutexEvenGenerator2());
    }

}
