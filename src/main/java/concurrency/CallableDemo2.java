package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class TaskWaitResult2 implements Callable<String> {
    private int i;

    public TaskWaitResult2(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return "task wait reuslt : " + i;
    }

}

public class CallableDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++)
            futures.add(exec.submit(new TaskWaitResult2(i)));

        exec.shutdown();
        TimeUnit.SECONDS.sleep(2);
        for (Future<String> future : futures)
            try {
                if (future.isDone())
                    System.out.println(future.get());
                else
                    System.out.println("task is not complete ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                // System.out.println("exec shut down ...");
                // exec.shutdown();
            }
    }
}
