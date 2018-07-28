package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomList1<T> {
	private List<T> list = new ArrayList<>();
	private Random random = new Random(47);
	
	void add(T ele) {
		list.add(ele);
	}
	
	T select() {
		return list.get(random.nextInt(list.size()));
	}
	

	public static void main(String[] args) {
		RandomList1<String> rl = new RandomList1<>();
		String[] array = "the quick fox jumped over lazy dog".split(" ");
		for(String s : array)
			rl.add(s);

		for(int i = 0; i < array.length; i++)
			System.out.print(rl.select() + " ");
	}
}