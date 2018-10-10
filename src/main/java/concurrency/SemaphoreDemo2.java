package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CheckoutTask2<T> implements Runnable {
	private static int count = 0;
	private final int id = count++;
	private Pool2<T> pool2;

	public CheckoutTask2(Pool2<T> pool2) {
		this.pool2 = pool2;
	}

	@Override
	public void run() {
		T t = pool2.checkOut();
		System.out.println(this + " is check out ...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool2.checkIn(t);
		System.out.println(this + " is check in ...");
	}

	@Override
	public String toString() {
		return "CheckoutTask2 " + id;
	}

}

public class SemaphoreDemo2 {
	static final int SIZE = 10;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Pool2<Fat> pool2 = new Pool2<>(Fat.class, SIZE);

		for (int i = 0; i < SIZE; i++)
			exec.submit(new CheckoutTask2<Fat>(pool2));
		System.out.println("all task is created in the pool2 ...");
		List<Fat> fats = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			Fat fat = pool2.checkOut();
			fats.add(fat);
		}
		Future<?> future = exec.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread blocked ...");
				Fat fat = pool2.checkOut();
				System.out.println("thread is through ..." + fat.getClass()
						.getName());
			}
		});
		for (Fat fat : fats) {
			fat.operation();
			pool2.checkIn(fat);
		}
		for (Fat fat : fats) {
			System.out.println("before block ...");
			pool2.checkIn(fat);
			System.out.println("after block ...");
		}

		exec.shutdown();
	}

}
