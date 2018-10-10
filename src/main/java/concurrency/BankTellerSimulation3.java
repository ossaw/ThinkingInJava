package concurrency;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Customer3 {
	private int serviceTime;

	public Customer3(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getSeviceTime() {
		return serviceTime;
	}

	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}

class CustomerQueue3 extends ArrayBlockingQueue<Customer3> {
	private static final long serialVersionUID = -1133366555649358164L;

	public CustomerQueue3(int capacity) {
		super(capacity);
	}

	@Override
	public String toString() {
		if (this.isEmpty())
			return "[Empty]";

		StringBuilder sb = new StringBuilder();
		for (Customer3 customer3 : this) {
			sb.append(customer3 + " ");
		}
		return sb.toString();
	}
}

class CustomerGenerator3 implements Runnable {
	private CustomerQueue3 cq3;
	private static Random random = new Random(47);

	public CustomerGenerator3(CustomerQueue3 cq3) {
		this.cq3 = cq3;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				TimeUnit.MICROSECONDS.sleep(random.nextInt(300));
				cq3.put(new Customer3(random.nextInt(5000)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Teller3 implements Runnable, Comparable<Teller3> {
	private CustomerQueue3 cq3;
	private static int counter = 0;
	private final int id = counter++;
	private int serviceCount = 0;
	private boolean isServicingCustomer = true;

	public Teller3(CustomerQueue3 cq3) {
		this.cq3 = cq3;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Customer3 c3 = cq3.take();
				TimeUnit.MICROSECONDS.sleep(c3.getSeviceTime());
				synchronized (this) {
					serviceCount++;
					while (!isServicingCustomer)
						wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "T " + id;
	}

	@Override
	public synchronized int compareTo(Teller3 other) {
		return serviceCount < other.serviceCount ? -1
				: (serviceCount == other.serviceCount ? 0 : 1);
	}

	public synchronized void doSomethingElse() {
		serviceCount = 0;
		isServicingCustomer = false;
	}

	public synchronized void sevicingCustomer() {
		assert !isServicingCustomer : "Already service " + this;
		isServicingCustomer = true;
		notifyAll();
	}
}

class TellerManager3 implements Runnable {
	private ExecutorService exec;
	private int adjustTime;
	private CustomerQueue3 cq3;
	private PriorityQueue<Teller3> pq = new PriorityQueue<>();
	private Queue<Teller3> doSomethingElseQueue = new LinkedList<>();

	public TellerManager3(ExecutorService exec, int adjustTime,
			CustomerQueue3 cq3) {
		this.adjustTime = adjustTime;
		this.exec = exec;
		this.cq3 = cq3;
		Teller3 t3 = new Teller3(cq3);
		exec.execute(t3);
		pq.add(t3);
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				TimeUnit.MICROSECONDS.sleep(adjustTime);
				adjustTellerNumber();
				System.out.print(cq3 + " {");
				for (Teller3 t3 : pq)
					System.out.print(t3 + " ");
				System.out.println(" } ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void adjustTellerNumber() {
		if (cq3.size() / pq.size() > 2) {
			if (!doSomethingElseQueue.isEmpty()) {
				Teller3 t3 = doSomethingElseQueue.remove();
				t3.sevicingCustomer();
				pq.offer(t3);
				return;
			}
			Teller3 t3 = new Teller3(cq3);
			exec.execute(t3);
			pq.add(t3);
			return;
		}
		if (pq.size() > 1 && cq3.size() / pq.size() <= 2)
			reassignOneTeller();
		if (cq3.isEmpty())
			while (pq.size() > 1)
				reassignOneTeller();
	}

	private void reassignOneTeller() {
		Teller3 t3 = pq.poll();
		t3.doSomethingElse();
		doSomethingElseQueue.offer(t3);
	}
}

public class BankTellerSimulation3 {
	static final int SIZE = 50;
	static final int ADJUST_TIME = 1000;

	public static void main(String[] args) throws NumberFormatException,
			InterruptedException, IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerQueue3 cq3 = new CustomerQueue3(SIZE);
		CustomerGenerator3 cg3 = new CustomerGenerator3(cq3);
		exec.execute(cg3);
		exec.execute(new TellerManager3(exec, ADJUST_TIME, cq3));

		if (args.length == 1)
			TimeUnit.SECONDS.sleep(Long.valueOf(args[0]));
		else
			System.in.read();
		exec.shutdownNow();
	}
}
