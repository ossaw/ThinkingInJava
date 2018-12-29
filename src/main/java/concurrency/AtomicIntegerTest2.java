package concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest2 implements Runnable {
    // 使用此类时注意初始化，否则会空指针异常
    private AtomicInteger val = new AtomicInteger(0);

    public int getVal() {
        return val.get();
    }

    public void evenIncreament() {
        val.addAndGet(2);
    }

    @Override
    public void run() {
        while (true)
            evenIncreament();
    }

    public static void main(String[] args) throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("stop ...");
                // System.exit(0);
            }
        }, 1000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest2 ait2 = new AtomicIntegerTest2();
        exec.execute(ait2);
        while (true) {
            int res = ait2.getVal();
            if (res % 2 != 0) {
                System.err.println(res + " is not even!");
                System.exit(0);
            }
        }
    }
}
