package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool2 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++)
			exec.execute(new LiftOff2(4));

		exec.shutdown();
		System.out.println("main thread ...");
	}

}
