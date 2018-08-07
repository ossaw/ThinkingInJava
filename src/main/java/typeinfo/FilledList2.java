package typeinfo;

import java.util.ArrayList;
import java.util.List;

class CountedInteger2 {
	private static int count = 0;
	private final int id = count++;

	@Override
	public String toString() {
		return Long.toString(id);
	}
}

public class FilledList2<T> {
	private Class<T> type;

	public FilledList2(Class<T> type) {
		this.type = type;
	}
	
	public List<T> create(int n) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < n; i++)
			try {
				result.add(type.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public static void main(String[] args) {
		FilledList2<CountedInteger2> filledList2 = new FilledList2<>(CountedInteger2.class);
		System.out.println(filledList2.create(10));
	}

}
