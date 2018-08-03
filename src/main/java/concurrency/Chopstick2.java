package concurrency;

public class Chopstick2 {
	// 筷子默认没有被拿走
	private boolean taken = false;
	
	public synchronized void take() throws InterruptedException {
		while (taken)
			// wait 操作会释放锁
			wait();
		taken = true;
	}
	
	public synchronized void drop() {
		taken = false;
		// 唤醒其他等待筷子的哲学家
		notifyAll();
	}

}
