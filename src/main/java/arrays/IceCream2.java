package arrays;

import java.util.Arrays;
import java.util.Random;

public class IceCream2 {
	private static Random random = new Random(47);
	private static final String[] FLAVORS = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };

	private static String[] flavorSet(int n) {
		if (n > FLAVORS.length)
			throw new IllegalArgumentException("Size is big ...");
		String[] result = new String[n];
		boolean[] flag = new boolean[FLAVORS.length];
		for (int i = 0; i < n; i++) {
			int t;
			do
				t = random.nextInt(FLAVORS.length);
			while (flag[t]);
			result[i] = FLAVORS[t];
			flag[t] = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 7; i++)
			System.out.println(Arrays.toString(flavorSet(3)));
	}

}
