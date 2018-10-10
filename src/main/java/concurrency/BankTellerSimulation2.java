package concurrency;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Customer2 {
	private final int serviceTime;

	public Customer2(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}

class CustomerLine2 extends ArrayBlockingQueue<Customer2> {
	private static final long serialVersionUID = -2225384576792644370L;

	public CustomerLine2(int size) {
		super(size);
	}

	@Override
	public String toString() {
		if (this.isEmpty())
			return "[Empty]";
		StringBuffer sb = new StringBuffer();
		for (Customer2 customer2 : this)
			sb.append(customer2 + " ");
		return sb.toString();
	}
}

class CustomerLineGenerator2 implements Runnable {
	private CustomerLine2 customerLine2;
	private static Random random = new Random(47);

	public CustomerLineGenerator2(CustomerLine2 customerLine2) {
		this.customerLine2 = customerLine2;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				TimeUnit.MICROSECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			customerLine2.offer(new Customer2(random.nextInt(1000)));
		}
		System.out.println("customer queue is full ...");
	}
}

class Teller2 implements Runnable, Comparable<Teller2> {
	private static int count = 0;
	private final int id = count++;
	private int servingCustomerCount = 0;
	private boolean isServingCustomer = true;
	private CustomerLine2 customerLine2;

	public Teller2(CustomerLine2 customerLine2) {
		this.customerLine2 = customerLine2;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Customer2 customer2 = customerLine2.take();
				TimeUnit.MICROSECONDS.sleep(customer2.getServiceTime());

				servingCustomerCount++;
				while (!isServingCustomer)
					wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this + " is service over ...");
	}

	public void doSomeThingElse() {
		servingCustomerCount = 0;
		isServingCustomer = false;
	}

	public void servingCustomer() {
		assert !isServingCustomer : this;
		isServingCustomer = true;
		notifyAll();
	}

	@Override
	public String toString() {
		return "teller " + id + " is already serving " + servingCustomerCount;
	}

	@Override
	public int compareTo(Teller2 other) {
		return 0;
	}
}

class TellerManager2 implements Runnable {
	private PriorityQueue<Teller2> workingTellers = new PriorityQueue<>();
	private ExecutorService exec;
	private CustomerLine2 customerLine2;
	private int adjustmentPeriod;
	private Queue<Teller2> doOtherThingQueue = new LinkedList<>();

	public TellerManager2(int adjustmentPeriod, ExecutorService exec,
			CustomerLine2 customerLine2) {
		this.adjustmentPeriod = adjustmentPeriod;
		this.customerLine2 = customerLine2;
		this.exec = exec;
		Teller2 teller2 = new Teller2(customerLine2);
		exec.execute(teller2);
		workingTellers.offer(teller2);
	}

	@Override
	public void run() {
		while (Thread.interrupted()) {
			try {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print("{ ");
				for (Teller2 teller2 : workingTellers)
					System.out.print(teller2);
				System.out.print(" }");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void adjustTellerNumber() {
		// This is actually a control system. By adjusting
		// the numbers, you can reveal stability issues in
		// the control mechanism.
		// If line is too long, add another teller:
		if (customerLine2.size() / workingTellers.size() > 2) {
			// If tellers are on break or doing
			// another job, bring one back:
			if (doOtherThingQueue.size() > 0) {
				Teller2 teller2 = doOtherThingQueue.remove();
				teller2.servingCustomer();
				workingTellers.offer(teller2);
				return;
			}
			// Else create (hire) a new teller
			Teller2 teller2 = new Teller2(customerLine2);
			exec.execute(teller2);
			workingTellers.add(teller2);
			return;
		}
		// If line is short enough, remove a teller:
		if (workingTellers.size() > 1 && customerLine2.size() / workingTellers
				.size() < 2)
			reassignOneTeller();
		// If there is no line, we only need one teller:
		if (customerLine2.size() == 0)
			while (workingTellers.size() > 1)
				reassignOneTeller();
	}

	// Give a teller a different job or a break:
	private void reassignOneTeller() {
		Teller2 teller2 = workingTellers.poll();
		teller2.doSomeThingElse();
		doOtherThingQueue.offer(teller2);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}

public class BankTellerSimulation2 {
	static final int ADJUSTMENT_PERIOD = 100;
	static final int QUEUE_SIZE = 10;

	public static void main(String[] args) throws InterruptedException {
		CustomerLine2 customerLine2 = new CustomerLine2(QUEUE_SIZE);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new CustomerLineGenerator2(customerLine2));
		exec.execute(new TellerManager2(ADJUSTMENT_PERIOD, exec,
				customerLine2));

		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}
