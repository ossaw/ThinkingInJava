package concurrency;

public class LiftOff2 implements Runnable {
	private int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff2() {}

	public LiftOff2(int countDown) {
		this.countDown = countDown;
	}

	private String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + ")";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			Thread.yield();
		}
	}

}
