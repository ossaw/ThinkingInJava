package concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher2 implements Runnable {
	private final int id;
	private final int ponderFactor;
	private Chopstick2 left;
	private Chopstick2 right;
	private Random rand = new Random(47);

	public Philosopher2(Chopstick2 left, Chopstick2 right, int id,
			int ponderFactor) {
		this.id = id;
		this.ponderFactor = ponderFactor;
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			// Philosopher is thinking ...
			System.out.println(this + " is thinking ...");
			pause();
			// Philosopher is becoming hungry ...
			try {
				left.take();
				System.out.println(this + " take left chopstick ...");
				right.take();
				System.out.println(this + " take right chopstick ...");
			} catch (InterruptedException e) {
				System.err.println("take chopstick is interrupted ...");
			}
			// Philosopher eating ...
			System.out.println(this + " is eating ...");
			pause();

			// Philosopher drop left and right chopstick ...
			System.out.println(this + " is drop chopstick ...");
			left.drop();
			right.drop();
		}
	}

	public void pause() {
		if (ponderFactor == 0)
			return;
		try {
			// 模拟哲学家思考和进餐时间
			TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor * 250));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philsopher2 " + id;
	}

}
