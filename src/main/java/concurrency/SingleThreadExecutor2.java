package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor2 {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff2(4));
		}
		exec.shutdown();
		System.out.println("main thread ...");
	}

}
