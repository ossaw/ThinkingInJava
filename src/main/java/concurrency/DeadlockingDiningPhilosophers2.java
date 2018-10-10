package concurrency;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockingDiningPhilosophers2 {

	public static void main(String[] args) throws InterruptedException,
			IOException {
		int size = 5;
		if (args.length > 0)
			size = Integer.parseInt(args[0]);
		Chopstick2[] stick2 = new Chopstick2[size];
		int ponderFactor = 5;
		if (args.length > 1)
			ponderFactor = Integer.parseInt(args[1]);
		for (int i = 0; i < size; i++)
			stick2[i] = new Chopstick2();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++)
			exec.execute(new Philosopher2(stick2[i], stick2[(i + 1) % size], i,
					ponderFactor));

		if (args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}

}
