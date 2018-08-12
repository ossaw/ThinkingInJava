package generics;

import java.util.ArrayList;
import java.util.Collection;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.Generator;

public class Generators2 {
	static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int size) {
		for (int i = 0; i < size; i++)
			coll.add(gen.next());
		return coll;
	}
	
	public static void main(String[] args) {
		Collection<Coffee> coll = fill(new ArrayList<>(), new CoffeeGenerator(), 5);
		for (Coffee coffee : coll)
			System.out.println(coffee);
		Collection<Integer> cInteger = fill(new ArrayList<>(), new Fibonacci(), 10);
		for (Integer integer : cInteger) {
			System.out.print(integer + " ");
		}
	}

}
