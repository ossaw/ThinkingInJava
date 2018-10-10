package generics.coffee;

import java.util.Iterator;
import java.util.Random;

import net.mindview.util.Generator2;

public class CoffeeGenerator2 implements Generator2<Coffee2>,
		Iterable<Coffee2> {
	private Class<?>[] types = new Class<?>[] { Latte2.class, Cappuccino2.class,
			Americano2.class, Mocha2.class, Breve2.class };
	private static Random random = new Random(47);
	private int size;

	public CoffeeGenerator2() {}

	public CoffeeGenerator2(int size) {
		this.size = size;
	}

	private class CoffeeIterator implements Iterator<Coffee2> {
		int count = size;

		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public Coffee2 next() {
			count--;
			return CoffeeGenerator2.this.next();
		}
	}

	@Override
	public Iterator<Coffee2> iterator() {
		return new CoffeeIterator();
	}

	@Override
	public Coffee2 next() {
		try {
			return (Coffee2) types[random.nextInt(types.length)].newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		CoffeeGenerator2 cg2 = new CoffeeGenerator2(5);
		for (int i = 0; i < 5; i++)
			System.out.println(cg2.next());

		System.out.println();

		for (Coffee2 c2 : cg2)
			System.out.println(c2);

		System.out.println();

		cg2 = new CoffeeGenerator2(5);
		Iterator<Coffee2> coffeeIterator = cg2.iterator();
		while (coffeeIterator.hasNext())
			System.out.println(coffeeIterator.next());
	}

}
