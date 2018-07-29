package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker2 implements Runnable {
	private IntGenerator2 ig2;
	private final int id;

	public EvenChecker2(IntGenerator2 ig2, int id) {
		this.id = id;
		this.ig2 = ig2;
	}

	@Override
	public void run() {
		while (!ig2.isCancel()) {
			int val = ig2.next();
			if (val % 2 != 0) {
				System.out.println("error, " + val + " is not even");
				ig2.cancel();
			}
		}
	}

	public static void test(IntGenerator2 ig2, int count) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++)
			exec.execute(new EvenChecker2(ig2, i));
		exec.shutdown();
	}

	public static void test(IntGenerator2 ig2) {
		test(ig2, 10);
	}

}
