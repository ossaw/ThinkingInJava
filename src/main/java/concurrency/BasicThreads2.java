package concurrency;

public class BasicThreads2 {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i ++)
			new Thread(new LiftOff2(5)).start();
		System.out.println("main thread ...");
	}

}
