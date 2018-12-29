package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Accessor2 implements Runnable {
    // id为类标识，一旦赋值不能修改
    private final int id;

    public Accessor2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder2.increament();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "# " + id + " thread local " + ThreadLocalVariableHolder2.get();
    }
}

public class ThreadLocalVariableHolder2 {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);

        // 加锁保证线程安全
        protected synchronized Integer initialValue() {
            return random.nextInt(1000);
        };
    };

    public static void increament() {
        value.set(value.get() + 1);
    }

    public static Integer get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor2(i));

        TimeUnit.SECONDS.sleep(2);
        // 线程run方法一直循环，所以此处不调用shutdown不会立即停止，。
        // exec.shutdown();
        exec.shutdownNow();
    }

}
