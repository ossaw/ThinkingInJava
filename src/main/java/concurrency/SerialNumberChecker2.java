package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CircularSet2 {
    private int[] array;
    private int len;
    private int index;

    public CircularSet2(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < size; i++)
            array[i] = -1;
    }

    // 加锁保证容器内两部操作的原子性，否则会使容器下标值紊乱，报下表越界异常。
    public synchronized void add(int i) {
        array[index] = i;
        // 更新容器下标值，防止容器过载
        index = ++index % len;
    }

    // 如果此方法不加锁，在add方法执行前后会有多个线程进来便利array，
    // add方法执行前后的array值不一致，会使contain方法不准确
    public synchronized boolean cointain(int i) {
        for (int a : array)
            if (a == i)
                return true;
        return false;
    }

}

public class SerialNumberChecker2 {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    private static CircularSet2 cs2 = new CircularSet2(1000);
    private static final int SIZE = 10;

    // 多线程访问共享资源
    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++)
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        int nsn = SerialNumberGenerator.nextSerialNumber();
                        if (cs2.cointain(nsn)) {
                            System.out.println("cs2 already contain " + nsn
                                    + " ...");
                            System.exit(0);
                        }
                        cs2.add(nsn);
                    }
                }
            });
    }
}
