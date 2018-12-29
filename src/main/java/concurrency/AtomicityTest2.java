package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest2 implements Runnable {
    // val修改为volatile修饰，再次测试。
    private int val = 0;

    public int getVal() {
        return val;
    }

    public synchronized void valIncreament() {
        val++;
        val++;
    }

    @Override
    public void run() {
        while (true)
            valIncreament();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest2 at2 = new AtomicityTest2();
        exec.execute(at2);
        // 此处要点：线程池执行和主线程是并行执行的，即使valIncreament()加锁，
        // 但是getVal()没加锁，下面循环会读取i值会不准确，即使val值为volatile，
        // 也只保持可见性，不会保持原子性。
        while (true) {
            int res = at2.getVal();
            if (res % 2 != 0) {
                System.out.println(res + " is not even!");
                System.exit(0);
            }
        }

    }
}
