package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BerylliumSphere2 {
	private static int count = 0;
	private final int id = count++;

	@Override
	public String toString() {
		return "Sphere2 " + id;
	}

}

public class ContainerComparison2 {

	public static void main(String[] args) {
		BerylliumSphere2[] bsArray = new BerylliumSphere2[10];
		for (int i = 0; i < 5; i++) {
			bsArray[i] = new BerylliumSphere2();
		}
		System.out.println(Arrays.toString(bsArray));
		System.out.println(bsArray[4]);

		List<BerylliumSphere2> berylliumSphere2s = new ArrayList<BerylliumSphere2>();
		for (int i = 0; i < 5; i++) {
			berylliumSphere2s.add(new BerylliumSphere2());
		}
		System.out.println(berylliumSphere2s);
		System.out.println(berylliumSphere2s.get(4));

		int[] array = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(array));
		System.out.println(array[4]);

		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(list);
		System.out.println(list.get(4));
	}

}
