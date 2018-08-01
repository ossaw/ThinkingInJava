package concurrency;

import static net.mindview.util.Print.print;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count2 {
	private int count = 0;
	
	public synchronized void increament() {
		count++;
	}
	
	public synchronized int getCount() {
		return count;
	}

}

class Entrance2 implements Runnable {
	private final int id;
	private static List<Entrance2> entrance2s = new ArrayList<>();
	private static Count2 c2 = new Count2();
	private static volatile boolean cancel = false;
	private int number;

	public Entrance2(int id) {
		this.id = id;
		entrance2s.add(this);
	}
	
	@Override
	public String toString() {
		return "Entrance: " + id + " number: " + number;
	}
	
	public synchronized static void cancel() {
		cancel = true;
	}
	
	public int getNumber() {
		return number;
	}
	
	// 此防范仅由主线程调用计算，可暂不加锁。
	public static int getSumNumber() {
		int sum = 0;
		for (Entrance2 entrance2 : entrance2s)
			sum += entrance2.getNumber();

		return sum;
	}

	public static int getCount() {
		return c2.getCount();
	}

	@Override
	public void run() {
		while (!cancel) {
			number++;
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c2.increament();
			System.out.println(this + " total count: " + c2.getCount());
		}
	}

}

public class OrnamentalGarden2 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new Entrance2(i));
		
		TimeUnit.SECONDS.sleep(1);
		Entrance2.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
			print("Some tasks were not terminated!");
		
		System.out.println("TOTAL COUNT :" + Entrance2.getCount());
		System.out.println("ENTRACE TOTAL NUMBER :" + Entrance2.getSumNumber());
	}
}
