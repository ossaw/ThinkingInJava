package concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WaitingTask2 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	private final CountDownLatch cdl;

	public WaitingTask2(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			cdl.await();
		} catch (InterruptedException e) {
			System.err.println("WaitingTask2 is interrupted ...");
		}
		System.out.println(this + " begin running ...");
	}

	@Override
	public String toString() {
		return "waiting task2 " + id;
	}

}

class TaskPortion2 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	private final CountDownLatch cdl;
	private static Random random = new Random(47);

	public TaskPortion2(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		doWork();
		// 必须在doWork方法执行过后才能执行countDown方法，
		// 否则countDown即将为0时线程切换可能导致CountDownLatch失效
		cdl.countDown();
	}

	private void doWork() {
		try {
			TimeUnit.MICROSECONDS.sleep(random.nextInt(200));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(this + " is complete ...");
	}

	@Override
	public String toString() {
		return "task portion2 " + id;
	}
}

public class CountDownLatchDemo2 {
	private static final ExecutorService exec = Executors.newCachedThreadPool();
	private static final int SIZE = 100;
	// 两个线程类得用一个CountDownLatch对象
	private static final CountDownLatch cdl = new CountDownLatch(SIZE);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++)
			exec.execute(new WaitingTask2(cdl));
		for (int i = 0; i < SIZE; i++)
			exec.execute(new TaskPortion2(cdl));

		exec.shutdown();
	}

}
