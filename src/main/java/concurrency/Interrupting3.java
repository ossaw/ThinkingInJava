package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocked3 implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.err.println("Interrupting ...");
		}
		System.out.println("exit sleep blocked ...");
	}
}

class IOBlocked3 implements Runnable {
	private InputStream in;

	public IOBlocked3(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		System.out.println("wait for to read ...");
		try {
			in.read();
		} catch (IOException e) {
			System.err.println("inputstream read exception ...");
		}
		System.out.println("exit io blocked ...");
	}
}

class SynchronizedBlocked3 implements Runnable {
	public SynchronizedBlocked3() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				f();
			}
		}).start();
	}

	public synchronized void f() {
		while (true)
			Thread.yield();
	}

	@Override
	public void run() {
		System.out.println("try to execute f()");
		f();
		System.out.println("exit synchronized blocked ...");
	}
}

public class Interrupting3 {
	private static ExecutorService exec = Executors.newCachedThreadPool();

	private static void test(Runnable task) throws InterruptedException {
		Future<?> future = exec.submit(task);
		TimeUnit.MICROSECONDS.sleep(100);
		future.cancel(true);
	}

	public static void main(String[] args) throws InterruptedException {
		test(new SleepBlocked3());
		test(new IOBlocked3(System.in));
		test(new SynchronizedBlocked3());

		TimeUnit.SECONDS.sleep(3);
		System.out.println("abort already over ...");
		System.exit(0);
	}

}
