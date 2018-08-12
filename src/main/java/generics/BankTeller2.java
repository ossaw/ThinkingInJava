package generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import net.mindview.util.Generator;

class Customer2 {
	private static int counter = 0;
	private final int id = counter++;
	
	private Customer2() {
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
	
	public static Generator<Customer2> generator() {
		return new Generator<Customer2>() {
			@Override
			public Customer2 next() {
				return new Customer2();
			}
		};
	}
	
}

class Teller2 {
	private static int counter = 0;
	private final int id = counter++;
	public static Generator<Teller2> generator = new Generator<Teller2>() {
		public Teller2 next() {
			return new Teller2();
		};
	};
	
	private Teller2() {
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + id;
	}
}

public class BankTeller2 {
	public static void main(String[] args) {
		Random random = new Random(47);
		Queue<Customer2> queue = new LinkedList<>();
		Generators2.fill(queue, Customer2.generator(), 10);
		List<Teller2> tellers = new ArrayList<>();
		Generators2.fill(tellers, Teller2.generator, 5);
		for (Customer2 c : queue)
			server(tellers.get(random.nextInt(tellers.size())), c);
	}

	static void server(Teller2 t, Customer2 c) {
		System.out.println(t + " severs " + c);
	}

}
