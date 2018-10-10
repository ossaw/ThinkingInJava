package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericVarargs2 {

	public static <T> List<T> makeList(T... args) {
		List<T> list = new ArrayList<>();
		for (T t : args)
			list.add(t);
		return list;
	}

	public static void main(String[] args) {
		List<String> list = makeList("A");
		System.out.println(list);
		list = makeList("A", "B", "C");
		System.out.println(list);
		list = makeList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split(""));
		System.out.println(list);
	}

}
