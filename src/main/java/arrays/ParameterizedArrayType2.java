package arrays;

import java.util.Arrays;

class ClassParameter2<T> {
	public T[] f(T[] args) {
		return args;
	}
}

class MethodParameter2 {
	public static <T> T[] f(T[] args) {
		return args;
	}
}

public class ParameterizedArrayType2 {
	public static void main(String[] args) {
		Integer[] ints = { 1, 2, 3, 4, 5 };
		Double[] doubles = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		Integer[] ints2 = new ClassParameter2<Integer>().f(ints);
		Double[] doubles2 = MethodParameter2.f(doubles);

		System.out.println(Arrays.toString(ints2));
		System.out.println(Arrays.toString(doubles2));
	}
}
