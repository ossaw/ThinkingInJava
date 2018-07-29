//: concurrency/SleepingTask.java
package concurrency; /* Added by Eclipse.py */

// Calling sleep() to pause for a while.

import java.util.concurrent.*;

// 线程睡眠时不会影响其他线程执行
public class SleepingTask extends LiftOff {
	public void run() {
		try {
			while (countDown-- > 0) {
				System.out.print(status());
				// Old-style:
				// Thread.sleep(100);
				// Java SE5/6-style:
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.err.println("Interrupted");
		}
	}
	@Override
	public String status() {
		return "*" + id + "(" + (countDown > 0 ? countDown : "sleeping task!") + "), ";
	}

	public SleepingTask() {
	}

	public SleepingTask(int countDown) {
		this.countDown = countDown;
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++)
			exec.execute(new SleepingTask(2));
		for (int i = 0; i < 2; i++)
			exec.execute(new LiftOff(2));
		exec.shutdown();
	}
}
/*
 * Output: #0(9), #1(9), #2(9), #3(9), #4(9), #0(8), #1(8), #2(8), #3(8), #4(8),
 * #0(7), #1(7), #2(7), #3(7), #4(7), #0(6), #1(6), #2(6), #3(6), #4(6), #0(5),
 * #1(5), #2(5), #3(5), #4(5), #0(4), #1(4), #2(4), #3(4), #4(4), #0(3), #1(3),
 * #2(3), #3(3), #4(3), #0(2), #1(2), #2(2), #3(2), #4(2), #0(1), #1(1), #2(1),
 * #3(1), #4(1), #0(Liftoff!), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!),
 * #4(Liftoff!),
 */// :~
