package generics.coffee;

import java.util.Iterator;
import java.util.Random;

import net.mindview.util.Generator;

public class CoffeeGenerator1<T> implements Generator<T>, Iterable<T> {
	private Random random = new Random(47);
	private int size;
	@SuppressWarnings("unchecked")
	private Class<T>[] types = new Class[] { Latte.class, Mocha.class,
			Cappuccino.class, Americano.class, Breve.class };

	public CoffeeGenerator1(int size) {
		this.size = size;
	}

	@Override
	public Iterator<T> iterator() {
		return new CoffeeIterator1();
	}

	@Override
	public T next() {
		try {
			return types[random.nextInt(types.length)].newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	class CoffeeIterator1 implements Iterator<T> {
		int count = size;

		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public T next() {
			count--;
			return CoffeeIterator1.this.next();
		}

		@Override
		public void remove() {}

	}

	public static void main(String[] args) {

	}

}
