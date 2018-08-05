package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Horse2 implements Runnable {
	private final CyclicBarrier cb;
	private static int count = 0;
	private final int id = count++;
	private int strides = 0;
	// Random为伪随机序列，此处生命为静态的，且nextInt线程安全
	private static Random random = new Random(47);
	
	public Horse2(CyclicBarrier cb) {
		this.cb = cb;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			synchronized (this) {
				strides += random.nextInt(3);
			}
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "Horse2 id " + id + " ";
	}

	public synchronized int getStrides() {
		return strides;
	}

	public synchronized String tracks() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < getStrides(); i++)
			sb.append("*");
		sb.append(" " + id);
			
		return sb.toString();
	}
	
}

public class HorseRace2 {
	private List<Horse2> horse2s = new ArrayList<Horse2>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier cb;
	private static final int FINAL_LINE = 50;

	public HorseRace2(int nHorses, int pause) {
		cb = new CyclicBarrier(nHorses, new Runnable() {
			@Override
			public void run() {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < FINAL_LINE; i++)
					sb.append("=");
				System.err.println(sb);
				for (Horse2 horse2 : horse2s)
					System.out.println(horse2.tracks());
				for (Horse2 horse2 : horse2s)
					if (horse2.getStrides() >= FINAL_LINE) {
						System.out.println(horse2 + " win ...");
						exec.shutdownNow();
						return;
					}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
			}
		});
		for (int i = 0; i < nHorses; i++) {
			Horse2 horse2 = new Horse2(cb);
			horse2s.add(horse2);
			exec.execute(horse2);
		}

	}
	public static void main(String[] args) {
		int nHorse = 7;
		if (args.length > 0) {
			int n = new Integer(args[0]);
			nHorse = n > 0 ? n : nHorse;
		}
		int pause = 200;
		if (args.length > 1) {
			int p = new Integer(args[1]);
			pause = p > -1 ? p : pause;
		}
		new HorseRace2(nHorse, pause);
		
	}

}